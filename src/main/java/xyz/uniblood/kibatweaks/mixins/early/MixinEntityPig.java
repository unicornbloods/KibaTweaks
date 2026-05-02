package xyz.uniblood.kibatweaks.mixins.early;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityPig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPig.class, remap = false)
public class MixinEntityPig {

    @Inject(
        method = "onStruckByLightning(Lnet/minecraft/entity/effect/EntityLightningBolt;)V",
        at = @At("HEAD"),
        cancellable = true)
    public void AntiPigman(EntityLightningBolt lightningBolt, CallbackInfo ci) {
        ci.cancel();
    }

}
