package net.kuko.alwaystrader.mixin;


import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MerchantOffer.class)
public class MerchantOfferMixin {

    @Inject(method = "isOutOfStock", at = @At("HEAD"), cancellable = true)
    private void neverOutOfStock(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}