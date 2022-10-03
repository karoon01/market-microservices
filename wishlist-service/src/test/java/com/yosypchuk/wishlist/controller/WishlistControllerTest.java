package com.yosypchuk.wishlist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yosypchuk.wishlist.model.Wishlist;
import com.yosypchuk.wishlist.model.WishlistItem;
import com.yosypchuk.wishlist.service.WishlistItemService;
import com.yosypchuk.wishlist.service.WishlistService;
import com.yosypchuk.wishlist.util.TestWishlistDataUtil;
import com.yosypchuk.wishlist.util.TestWishlistItemDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.yosypchuk.wishlist.util.TestWishlistDataUtil.*;
import static com.yosypchuk.wishlist.util.TestWishlistItemDataUtil.MOCK_PRODUCT_ID;
import static com.yosypchuk.wishlist.util.TestWishlistItemDataUtil.MOCK_WISHLIST;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = WishlistController.class,
        excludeAutoConfiguration = {UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class}
)
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistService wishlistService;
    @MockBean
    private WishlistItemService wishlistItemService;

    private final static String WISHLIST_API = "/wishlist/";

    @Test
    public void testCreateWishlist() throws Exception {
        Wishlist wishlist = TestWishlistDataUtil.createWishlist();

        when(wishlistService.createWishlist(MOCK_USER_ID)).thenReturn(wishlist);

        mockMvc.perform(post(WISHLIST_API + MOCK_USER_ID)
                        .content(objectToJson(wishlist))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(MOCK_ID))
                .andExpect(jsonPath("$.userId").value(MOCK_USER_ID));

        verify(wishlistService, times(1)).createWishlist(MOCK_USER_ID);
    }

    @Test
    public void testGetWishlistByUserId() throws Exception {
        Wishlist wishlist = TestWishlistDataUtil.createWishlist();

        when(wishlistService.getWishlistByUserId(MOCK_USER_ID)).thenReturn(wishlist);

        mockMvc.perform(get(WISHLIST_API + MOCK_USER_ID)
                        .content(objectToJson(wishlist))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(MOCK_ID))
                .andExpect(jsonPath("$.userId").value(MOCK_USER_ID));

        verify(wishlistService, times(1)).getWishlistByUserId(MOCK_ID);
    }

    @Test
    void testAddProductToWishlist() throws Exception {
        doNothing().when(wishlistItemService).addWishlistItem(MOCK_USER_ID, MOCK_PRODUCT_ID);

        mockMvc.perform(post(WISHLIST_API + MOCK_USER_ID + "/add/" + MOCK_PRODUCT_ID))
                .andExpect(status().isNoContent());

        verify(wishlistItemService, times(1)).addWishlistItem(MOCK_USER_ID, MOCK_PRODUCT_ID);
    }

    @Test
    void testRemoveProductFromWishlist() throws Exception {
        doNothing().when(wishlistItemService).removeWishlistItem(MOCK_ID);

        mockMvc.perform(delete(WISHLIST_API + MOCK_ID + "/remove"))
                .andExpect(status().isNoContent());

        verify(wishlistItemService, times(1)).removeWishlistItem(MOCK_ID);
    }

    @Test
    void testRemoveAllProductsFromWishList() throws Exception {
        doNothing().when(wishlistItemService).removeAllWishlistItems(MOCK_WISHLIST.getId());

        mockMvc.perform(delete(WISHLIST_API + MOCK_WISHLIST.getId() + "/remove/all"))
                .andExpect(status().isNoContent());

        verify(wishlistItemService, times(1)).removeAllWishlistItems(MOCK_WISHLIST.getId());
    }


    public static String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }

}
