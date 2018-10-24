/*
 * Better Ore Clusters Mod by AnaRchX
 * Big thanks to warr1024!
 * 
 * Create big clumps of ores like clouds, per biome
 * Better Ore Clusters
 */

package net.minecraft.src;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BOCWorldGenOreLakes extends WorldGenerator
{
    private int blockIndex;
    private double cloudsize;
    private int looptime;
    private boolean loaded = false;
    
    /**
     * par1 = Set the block id of what you want to spawn.
     * par2 = size of the clumps in double (from 0.1 to 1 or bigger)
     * bigger numbers are very very bad!
     * par3 = how many times the loop is going to run (MOAR CLUMPS!!)
     */
    
    public static boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean CheckRadius(World par1, int par2, int par3)
    {
        double SOME_DOUBLE_VALUE = BOCSettings.hardcoreradius;
        int var3 = par1.getSpawnPoint().posX;
        int var4 = par1.getSpawnPoint().posZ;
        double var7 = (double)(var3 - par2);
        double var9 = (double)(var4 - par3);
        double var11 = (var7 * var7) + (var9 * var9);
        double var13 = SOME_DOUBLE_VALUE * (par1.worldInfo.getTerrainType() == WorldType.LARGE_BIOMES ? 4.0D : 1.0D);
        return var11 < var13 * var13;
    }
    
    public BOCWorldGenOreLakes(int par1, double par2, int par3)
    {
        this.blockIndex = par1;
        this.cloudsize = par2;
        this.looptime = par3;
    	try 
    	{
    		if (!loaded)
    		{
    			BOCSettings.main(null);
    			loaded=true;
			}
		}
    	catch (IOException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        par3 -= 8;

        for (par5 -= 8; par4 > 5 && par1World.isAirBlock(par3, par4, par5); --par4)
        {
            ;
        }

        if (par4 <= 4)
        {
            return false;
        }
        else
        {
            par4 -= 4;
            boolean[] var6 = new boolean[2048];
            int var7 = this.looptime+1;//par2Random.nextInt(this.looptime);//+ 4; //This determines how many times the loop will go for. 1 for low 6 for high
            int var8;

            for (var8 = 0; var8 < var7; ++var8)
            {
                double var9 = par2Random.nextDouble() * 6.0D + 3.0D;
                double var11 = par2Random.nextDouble() * 4.0D + 2.0D;
                double var13 = par2Random.nextDouble() * 6.0D + 3.0D;
                double var15 = par2Random.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
                double var17 = par2Random.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
                double var19 = par2Random.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

                for (int var21 = 1; var21 < 15; ++var21)
                {
                    for (int var22 = 1; var22 < 15; ++var22)
                    {
                        for (int var23 = 1; var23 < 7; ++var23)
                        {

                            double var24 = ((double)var21 - var15) / (var9 / 2.0D);
                            double var26 = ((double)var23 - var17) / (var11 / 2.0D);
                            double var28 = ((double)var22 - var19) / (var13 / 2.0D);
                            double var30 = var24 * var24 + var26 * var26 + var28 * var28;
                            if (var30 < cloudsize) //This determines how big the cloud is!! bigger number the further away you are.
                            {
                                var6[(var22 * 16 + var21) * 8 + var23] = true;
                            }
                            
                        }
                    }
                }
//                for (int i=0; i<2048; i++)
//                {
//                	var6[i] = true;
//                }
            }

            int var10;
            int var32;
            boolean var33;
            
            for (var8 = 0; var8 < 16; ++var8)
            {
                for (var32 = 0; var32 < 16; ++var32)
                {
                    for (var10 = 0; var10 < 8; ++var10)
                    {
                        if (var6[(var8 * 16 + var32) * 8 + var10])
                        {
                        	Material var12 = par1World.getBlockMaterial(par3 + var8, par4 + var10, par5 + var32);

                            if (var10 >= 4 && var12.isLiquid()) //If you are trying to place it in liquid STOP!
                            {
                                return false;
                            }
                            //If you are trying to place below Y:4 and it is Liquid and that ID is not your ID
                            if (var10 < 4 && !var12.isSolid() && par1World.getBlockId(par3 + var8, par4 + var10, par5 + var32) != this.blockIndex)
                            {
                                return false;
                            }
                            
                        	//This is a sphere!
                        	//Play around to create more shapes!
                            if (var12.isSolid() && var12 == Material.rock)
                            {
                            	par1World.setBlock(par3 + var8, par4 + var10, par5 + var32, this.blockIndex, 0, 2);
                            }
                        	//Seemingly Random spheres
                        	//par1World.setBlock(par3 + var8+var10, par4 + var10, par5 + var32+var10, this.blockIndex, 0, 2);
//                        	par1World.setBlock(par3 + var32+var10, par4 + var10, par5 + var8+var10, this.blockIndex, 0, 2);
//                        	par1World.setBlock(par3 + var8-var10, par4 + var10, par5 + var32-var10, this.blockIndex, 0, 2);
                        }
                    }
                }
            }
            return true;
        }
    }
}
