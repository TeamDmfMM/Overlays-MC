package com.wesserboy.overlays.entities;

import java.util.ArrayList;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityDummyArrow extends AbstractArrowEntity{

	public EntityDummyArrow(World worldIn, LivingEntity shooter) {
		super(EntityType.ARROW, shooter, worldIn);
		
		this.pickupStatus = ArrowEntity.PickupStatus.DISALLOWED;
	}

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}
	
	// These two overrides prevent the dummy-arrows from spawning water particles
	@Override
	public boolean isInWater() {
		return false;
	}
	
	@Override
	protected void doWaterSplashEffect() {}
	
	private boolean hasHit = false;
	
	@Override
	protected void onHit(RayTraceResult result) {
		if(result.getType() != RayTraceResult.Type.MISS) {
			hasHit = true;
			this.hitResult = result;
		}
	}
	
	private void calcPath(){
		ArrayList<Vec3d> path = new ArrayList<Vec3d>();
		
		while(!this.hasHit && this.getPosition().getY() > 0){
			BlockPos pos2 = this.getPosition();
			Vec3d pos = new Vec3d(pos2.getX(), pos2.getY(), pos2.getZ());
			path.add(pos);
			this.tick();
		}
		
		this.path =  path.toArray(new Vec3d[path.size()]);
	}
	
	private Vec3d[] path;
	private RayTraceResult hitResult;
	
	public Vec3d[] getPath(){
		if(this.path == null){
			calcPath();
		}
		
		return this.path;
	}
	
	public RayTraceResult getHit(){
		if(this.hitResult == null){
			this.calcPath();
		}
		
		return this.hitResult;
	}

}
