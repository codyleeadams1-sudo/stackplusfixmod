package chihalu.stackplusfix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;

/** Same overflow protection for the weight calculation done when a bundle is loaded/created. */
@Mixin(BundleContents.class)
public abstract class BundleContentsMixin {
	@WrapMethod(method = "computeContentWeight")
	private static Fraction stackplusfix$safeWeight(List<ItemStack> items, Operation<Fraction> original) {
		try {
			return original.call(items);
		} catch (ArithmeticException e) {
			// Treat an un-measurable bundle as full instead of crashing.
			return Fraction.ONE;
		}
	}
}
