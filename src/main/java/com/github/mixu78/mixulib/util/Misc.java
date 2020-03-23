package com.github.mixu78.mixulib.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class Misc {

    public static <T extends Entity> List<T> getEntitiesInRadius(BlockPos pos, double radius, World world, Class<T> entityType) {
        AxisAlignedBB AABB = new AxisAlignedBB(pos.getX()-radius, pos.getY()-radius, pos.getZ()-radius, pos.getX()+radius, pos.getY()+radius, pos.getZ()+radius);
        List<T> entities = world.getEntitiesWithinAABB(entityType, AABB);
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getDistance(pos.getX(), pos.getY(), pos.getZ()) > radius) {
                entities.remove(i);
                break;
            }
        }
        return entities;
    }

    public static <T extends Entity> List<T> getEntitiesInRadius(double x, double y, double z, double radius, World world, Class<T> entityType) {
        return getEntitiesInRadius(new BlockPos(x, y, z), radius, world, entityType);
    }

    public static List<EntityLivingBase> getAllEntitiesInRadius(double x, double y, double z, double radius, World world) {
        return getEntitiesInRadius(x, y, z, radius, world, EntityLivingBase.class);
    }

    public static List<EntityLivingBase> getAllEntitiesInRadius(BlockPos pos, double radius, World world) {
        return getEntitiesInRadius(pos.getX(), pos.getY(), pos.getZ(), radius, world, EntityLivingBase.class);
    }
}
