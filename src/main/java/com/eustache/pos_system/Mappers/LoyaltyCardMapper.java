package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.LoyaltyCard.Request.CreateLoyaltyCardDto;
import com.eustache.pos_system.DTO.LoyaltyCard.Response.LoyaltyResponseCardDto;
import com.eustache.pos_system.Entities.LoyaltyCard;
import com.eustache.pos_system.Helpers.LoyaltyCardStatus;
import org.springframework.stereotype.Component;

@Component
public class LoyaltyCardMapper {
    /**
     * Converts a CreateLoyaltyCardDto to a LoyaltyCard entity.
     * @param loyaltyCardDto CreateLoyaltyCardDto
     * @return LoyaltyCard
     */
    public LoyaltyCard toEntity(CreateLoyaltyCardDto loyaltyCardDto){
        LoyaltyCard loyaltyCard = new LoyaltyCard();
        loyaltyCard.setCardNumber(loyaltyCardDto.cardNumber());
        loyaltyCard.setQrCode(loyaltyCard.getQrCode());
        loyaltyCard.setPoints(0);
        loyaltyCard.setStatus(LoyaltyCardStatus.ACTIVE);
        return loyaltyCard;
    }

    /**
     * Converts a LoyaltyCard entity to a LoyaltyResponseCardDto.
     * @param loyaltyCard LoyaltyCard entity
     * @return LoyaltyResponseCardDto
     */
    public LoyaltyResponseCardDto toResponseFromLoyaltyCard(LoyaltyCard loyaltyCard){
        return new LoyaltyResponseCardDto(
          loyaltyCard.getCardNumber(),
          loyaltyCard.getPoints(),
          loyaltyCard.getQrCode(),
          loyaltyCard.getStatus()
        );
    }
}
