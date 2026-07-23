package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.LoyaltyCard.Request.CreateLoyaltyCardDto;
import com.eustache.pos_system.DTO.LoyaltyCard.Response.LoyaltyResponseCardDto;
import com.eustache.pos_system.Entities.LoyaltyCard;
import com.eustache.pos_system.Helpers.LoyaltyCardStatus;
import org.springframework.stereotype.Component;

@Component
public class LoyaltyCardMapper {
    public LoyaltyCard toEntity(CreateLoyaltyCardDto loyaltyCardDto){
        LoyaltyCard loyaltyCard = new LoyaltyCard();
        loyaltyCard.setCardNumber(loyaltyCardDto.cardNumber());
        loyaltyCard.setQrCode(loyaltyCard.getQrCode());
        loyaltyCard.setStatus(LoyaltyCardStatus.ACTIVE);
        return loyaltyCard;
    }

    public LoyaltyResponseCardDto toResponseFromLoyaltyCard(LoyaltyCard loyaltyCard){
        return new LoyaltyResponseCardDto(
          loyaltyCard.getCardNumber(),
          loyaltyCard.getQrCode(),
          loyaltyCard.getStatus()
        );
    }
}
