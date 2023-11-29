package hi.hello.service;

import hi.hello.domain.Item;
import hi.hello.repository.ItemSearchCond;
import hi.hello.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems(ItemSearchCond itemSearch);

    void deleteAll();
}
