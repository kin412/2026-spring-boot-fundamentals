package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    @Autowired
    private final ItemRepository itemRepository;

    //병합의 예시임. 준영속 상태의 item을 영속성 컨텍스트에 넣으면
    //빈 값도 그냥 null로 들어가버림.
    //이게 인서트의 경우는 어차피 없느값을 넣는거니까 상관없는데 업데이트의 경우
    //만약 몇몇값을 안넣거나 하면 그게 그냥 null로 들어가버림.
    //병합대신 변경감지를 써야함.
    @Transactional //  깊은곳이 또 걸려있으므로 이 메서드는 readonly가 아님.
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    //병합 대신 이방법으로 업데이트를 해야함. - 변경 감지
    //일단 엔티티를 꺼내와서 그후에 업데이트를 하던 뭘하던 하자
    @Transactional
    public void updateItem(Long itemId, Book param) {
        //findItem이 findone을 통해 영속성 컨텍스트에 존재 하므로 변경감지로 업데이트가 이루어짐.
        //업데이트 시에는 book 그자체를 바로 save로 밀어넣는게 위험함.
        //없는 값은 null로 변함.

        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());

        /*
        아무것도 호출할 필요없음. 이미 영속성 컨텍스트안에 있기때문에
        값이 바뀌면 변경 감지(dirty checking) 되어 1차캐시에 저장되고
        커밋 시점에 자동으로 업데이트가 됨.
        em.update같은건 없음.
         */

    }

    // @Transactional(readOnly = true) 적용됨
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }

}
