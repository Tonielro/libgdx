package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**Singleton class which manages the particle effects.
 * It's a utility class to ease particle batches management and particle effects update. */
public final class ParticleSystem implements RenderableProvider{
	private static ParticleSystem instance;
	
	public static ParticleSystem get(){
		if(instance == null)
			instance = new ParticleSystem();
		return instance;
	}
	
	private Array<ParticleBatch<?>> batches;
	private Array<ParticleEffect> effects;
	
	private ParticleSystem () {
		batches = new Array<ParticleBatch<?>>();
		effects = new Array<ParticleEffect>();
	}
	
	public void add(ParticleBatch<?> batch){
		batches.add(batch);
	}
	
	public void add(ParticleEffect effect){
		effects.add(effect);
	}
	
	public void remove(ParticleEffect effect){
		effects.removeValue(effect, true);
	}
	
	/**Removes all the effects added to the system */
	public void removeAll () {
		effects.clear();
	}
	
	public void update(){
		for(ParticleEffect effect : effects){
			effect.update();
		}
	}
	
	public void updateAndDraw(){
		for(ParticleEffect effect : effects){
			effect.update();
			effect.draw();
		}
	}

	public void begin(){
		for(ParticleBatch<?> batch : batches)
			batch.begin();
	}

	public void draw(){
		for(ParticleEffect effect : effects){
			effect.draw();
		}
	}
		
	public void end(){
		for(ParticleBatch<?> batch : batches)
			batch.end();
	}

	@Override
	public void getRenderables (Array<Renderable> renderables, Pool<Renderable> pool) {
		for(ParticleBatch<?> batch : batches)
			batch.getRenderables(renderables, pool);
	}

	public Array<ParticleBatch<?>> getBatches () {
		return batches;
	}
}
