package chihalu.stackplusfix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Vanilla measures bundle fullness with commons-lang3 Fraction (1 / maxStackSize per item).
 * StackPlus allows max stack sizes up to 1,000,000,000, so Fraction add/divide can overflow and
 * throw ArithmeticException, which crashes the game. Here an overflowing insert is simply refused.
 */
@Mixin(BundleContents.Mutable.class)
public abstract class BundleContentsMutableMixin {
	@org.spongepowered.asm.mixin.Unique
	private static final Logger STACKPLUS_FIX_LOG = LogManager.getLogger("StackPlusBundleFix");

	@WrapMethod(method = "tryInsert")
	private int stackplusfix$safeTryInsert(ItemStack stack, Operation<Integer> original) {
		try {
			return original.call(stack);
		} catch (ArithmeticException e) {
			STACKPLUS_FIX_LOG.warn("Bundle weight overflow prevented (insert refused): {}", e.getMessage());
			return 0;
		}
	}
}
