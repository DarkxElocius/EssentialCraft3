package ec3.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import ec3.api.MagicianTableUpgrades;
import ec3.common.tile.TileMagicianTable;
import ec3.utils.common.ECUtils;

@SideOnly(Side.CLIENT)
public class RenderMagicianTable extends TileEntitySpecialRenderer
{
    public static final ResourceLocation textures = new ResourceLocation("essentialcraft:textures/special/models/magicianTable.png");
    public static final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("essentialcraft:textures/special/models/MagicianTable.obj"));
    public static final IModelCustom cube = AdvancedModelLoader.loadModel(new ResourceLocation("essentialcraft:textures/special/models/Cube.obj"));

    public RenderMagicianTable()
    {
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(TileEntity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
    	RenderHelper.disableStandardItemLighting();
        GL11.glPushMatrix();
	        GL11.glTranslatef((float)p_76986_2_+0.5F, (float)p_76986_4_, (float)p_76986_6_+0.5F);
	        this.bindTexture(textures);
	        model.renderAll();
	        TileMagicianTable table = (TileMagicianTable) p_76986_1_;
	        if(table.upgrade != -1)
	        {
	        	this.bindTexture(MagicianTableUpgrades.upgradeTextures.get(table.upgrade));
	        	float scale = 0.99F;
	        	GL11.glTranslatef(0, 0.005F, 0);
	        	GL11.glScalef(scale, scale, scale);
	        	cube.renderAll();
	        }
        GL11.glPopMatrix();
        ECUtils.renderMRUBeam(p_76986_1_, 0, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        RenderHelper.enableStandardItemLighting();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(TileEntity p_110775_1_)
    {
        return textures;
    }

	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_,
			double p_147500_4_, double p_147500_6_, float p_147500_8_) {
		if(p_147500_1_.getWorldObj().getBlockMetadata(p_147500_1_.xCoord, p_147500_1_.yCoord, p_147500_1_.zCoord) == 0)
		this.doRender((TileEntity) p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_, 0);
	}
}