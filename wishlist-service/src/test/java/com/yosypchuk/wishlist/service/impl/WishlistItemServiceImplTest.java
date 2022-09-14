package com.yosypchuk.wishlist.service.impl;

import com.yosypchuk.wishlist.exception.EntityNotFoundException;
import com.yosypchuk.wishlist.model.WishlistItem;
import com.yosypchuk.wishlist.repository.WishlistItemRepository;
import com.yosypchuk.wishlist.util.TestWishlistItemDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.yosypchuk.wishlist.util.TestWishlistItemDataUtil.MOCK_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WishlistItemServiceImplTest {
    @InjectMocks
    private WishlistItemServiceImpl wishlistItemService;
    @Mock
    private WishlistItemRepository wishlistItemRepository;

    @Test
    void testAddWishlistItem() {
        //given


        //when
        //then
    }

    @Test
    void testGetWishlistItemById() {
        //given
        WishlistItem expectedWishlistItem = TestWishlistItemDataUtil.createWishlistItem();

        when(wishlistItemRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedWishlistItem));

        //when
        WishlistItem actualWishlistItem = wishlistItemService.getWishlistItemById(MOCK_ID);

        //then
        assertThat(actualWishlistItem, allOf(
                hasProperty("id", equalTo(expectedWishlistItem.getId())),
                hasProperty("wishlist", equalTo(expectedWishlistItem.getWishlist())),
                hasProperty("productId", equalTo(expectedWishlistItem.getProductId()))
        ));
    }

    @Test
    void testGetWishlistItemByIdThrowsExceptionIfWishlistItemDoesntExist() {
        //when
        when(wishlistItemRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> wishlistItemService.getWishlistItemById(MOCK_ID));
    }

    @Test
    void testRemoveWishlistItem() {
        //given
        WishlistItem wishlistItem = TestWishlistItemDataUtil.createWishlistItem();

        when(wishlistItemRepository.findById(MOCK_ID)).thenReturn(Optional.of(wishlistItem));
        doNothing().when(wishlistItemRepository).delete(wishlistItem);

        //when
        wishlistItemService.removeWishlistItem(MOCK_ID);

        //then
        verify(wishlistItemRepository, times(1)).delete(wishlistItem);
    }

    @Test
    void testRemoveWishlistItemThrowsExceptionIfWishlistItemDoesntExist() {
        //when
        when(wishlistItemRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> wishlistItemService.removeWishlistItem(MOCK_ID));
    }

    @Test
    void testRemoveAllWishlistItems() {
        //given
        WishlistItem wishlistItem = TestWishlistItemDataUtil.createWishlistItem();

        when(wishlistItemRepository.findById(MOCK_ID)).thenReturn(Optional.of(wishlistItem));
        doNothing().when(wishlistItemRepository).removeAllWishlistItemsFromWishlist(MOCK_ID);

        //when
        wishlistItemService.removeAllWishlistItems(MOCK_ID);

        //then
        verify(wishlistItemRepository, times(1)).removeAllWishlistItemsFromWishlist(MOCK_ID);
    }

    @Test
    void testRemoveAllWishlistItemsThrowsExceptionIfWishlistItemDoesntExist() {
        //when
        when(wishlistItemRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> wishlistItemService.removeWishlistItem(MOCK_ID));
    }
}
