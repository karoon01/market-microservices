package com.yosypchuk.wishlist.service.impl;

import com.yosypchuk.wishlist.exception.EntityAlreadyExistException;
import com.yosypchuk.wishlist.exception.EntityNotFoundException;
import com.yosypchuk.wishlist.model.Wishlist;
import com.yosypchuk.wishlist.repository.WishlistRepository;
import com.yosypchuk.wishlist.util.TestWishlistDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.yosypchuk.wishlist.util.TestWishlistDataUtil.MOCK_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static com.yosypchuk.wishlist.util.TestWishlistDataUtil.MOCK_USER_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceImplTest {
    @InjectMocks
    private WishlistServiceImpl wishlistService;
    @Mock
    private WishlistRepository wishlistRepository;

    @Test
    void testCreateWishlist() {
        //given
        Wishlist expectedWishlist = TestWishlistDataUtil.createWishlist();

        when(wishlistRepository.findWishlistByUserId(MOCK_USER_ID)).thenReturn(Optional.empty());
        when(wishlistRepository.save(any())).thenReturn(expectedWishlist);

        //when
        Wishlist actualWishlist = wishlistService.createWishlist(MOCK_USER_ID);

        //then
        assertThat(actualWishlist, allOf(
                hasProperty("wishlistItems", equalTo(expectedWishlist.getWishlistItems())),
                hasProperty("userId", equalTo(expectedWishlist.getUserId()))
        ));
    }

    @Test
    void testCreateWishlistThrowsExceptionIfWishlistAlreadyExist() {
        //given
        Wishlist wishlist = TestWishlistDataUtil.createWishlist();

        //when
        when(wishlistRepository.findWishlistByUserId(MOCK_USER_ID)).thenReturn(Optional.of(wishlist));

        //then
        assertThrows(EntityAlreadyExistException.class, () -> wishlistService.createWishlist(MOCK_USER_ID));
    }

    @Test
    void getWishlistByUserId() {
        //given
        Wishlist expectedWishlist = TestWishlistDataUtil.createWishlist();

        when(wishlistRepository.findWishlistByUserId(MOCK_USER_ID)).thenReturn(Optional.of(expectedWishlist));

        //when
        Wishlist actualWishlist = wishlistService.getWishlistByUserId(MOCK_USER_ID);

        //then
        assertThat(actualWishlist, allOf(
                hasProperty("id", equalTo(expectedWishlist.getId())),
                hasProperty("wishlistItems", equalTo(expectedWishlist.getWishlistItems())),
                hasProperty("userId", equalTo(expectedWishlist.getUserId()))
        ));
    }

    @Test
    void testGetWishlistById() {
        //given
        Wishlist expectedWishlist = TestWishlistDataUtil.createWishlist();

        when(wishlistRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedWishlist));

        //when
        Wishlist actualWishlist = wishlistService.getWishlistById(MOCK_ID);

        //then
        assertThat(actualWishlist, allOf(
                hasProperty("id", equalTo(expectedWishlist.getId())),
                hasProperty("wishlistItems", equalTo(expectedWishlist.getWishlistItems())),
                hasProperty("userId", equalTo(expectedWishlist.getUserId()))
        ));
    }

    @Test
    void testGetWishlistByIdThrowsExcptionIfWishlistNotFound() {
        //when
        when(wishlistRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, ()-> wishlistService.getWishlistById(MOCK_ID));
    }
}
