package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>, JpaSpecificationExecutor<Wishlist> {

    @Query(value = "INSERT INTO wishlist " +
            "(wishlist.user_id, wishlist.country_code3)" +
            "OUTPUT Inserted.wishlist_id " +
            "VALUES (?1, ?2)", nativeQuery = true)
    int newWishlistItem(int user_id, String countryCode3);

    @Query(value = "SELECT * " +
            "FROM wishlist " +
            "WHERE user_id = ?1", nativeQuery = true
    )
    List<Wishlist> getWishlistItems(int user_id);

    @Modifying
    @Query(value = "DELETE FROM wishlist " +
            "WHERE user_id = ?1 AND country_code3 = ?2", nativeQuery = true
    )
    void removeWishlistItem(int user_id, String countryCode3);
}
