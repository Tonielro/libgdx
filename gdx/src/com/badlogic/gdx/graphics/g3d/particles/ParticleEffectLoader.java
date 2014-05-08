package com.badlogic.gdx.graphics.g3d.particles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.AssetData;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

/**This class can save and load a {@link ParticleEffect}.
 * It should be added as {@link AsynchronousAssetLoader} to the {@link AssetManager} so it will be able to load the effects.
 * It's important to note that the two classes {@link ParticleEffectLoadParameter} and {@link ParticleEffectSaveParameter} should
 * be passed in whenever possible, because when present the batches settings will be loaded automatically.
 * When the load and save parameters are absent, once the effect will be created, one will have to set the required batches
 * manually otherwise the {@link ParticleController} instances contained inside the effect will not be able to render themselves. */
/** @author Inferno */
public class ParticleEffectLoader extends AsynchronousAssetLoader<ParticleEffect, ParticleEffectLoader.ParticleEffectLoadParameter> {
	protected Array<ObjectMap.Entry<String, ResourceData<ParticleEffect>>> items = new Array<ObjectMap.Entry<String, ResourceData<ParticleEffect>>>(); 
	
	public ParticleEffectLoader (FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync (AssetManager manager, String fileName, FileHandle file, ParticleEffectLoadParameter parameter) {}

	@Override
	public Array<AssetDescriptor> getDependencies (String fileName, FileHandle file, ParticleEffectLoadParameter parameter) {
		Json json = new Json();
		ResourceData<ParticleEffect> data = json.fromJson(ResourceData.class, file);
		Array<AssetData> assets = null;
		synchronized (items) {
			ObjectMap.Entry<String, ResourceData<ParticleEffect>> entry = new ObjectMap.Entry<String, ResourceData<ParticleEffect>>();
			entry.key = fileName;
			entry.value = data;
			items.add(entry);
			assets = data.getAssets();
		}
		
		Array<AssetDescriptor> descriptors = new Array<AssetDescriptor>();
		for(AssetData<?> assetData : assets){
			
			//If the asset doesn't exist try to load it from loading effect directory
			if (!resolve(assetData.filename).exists()){
				assetData.filename = file.parent().child(Gdx.files.absolute(assetData.filename).name()).path();
			}

			if(assetData.type == ParticleEffect.class){
				descriptors.add(new AssetDescriptor(assetData.filename, assetData.type, parameter));
			}
			else 
				descriptors.add(new AssetDescriptor(assetData.filename, assetData.type));
		}
		
		return descriptors;
		
	}
	
	public void save(ParticleEffect effect, ParticleEffectSaveParameter parameter) throws IOException{
		ResourceData<ParticleEffect> data =  new ResourceData<ParticleEffect>(effect);
		
		//effect assets
		effect.save(parameter.manager, data);
		
		//Batches configurations
		if(parameter.batches != null){
			for(ParticleBatch<?> batch : parameter.batches){
				boolean save = false;
				for(ParticleController controller : effect.getControllers()){
					if(controller.renderer.isCompatible(batch)){
						save = true;
						break;
					}
				}
				
				if(save)
					batch.save(parameter.manager, data);
			}
		}
		
		//save
		Writer fileWriter = new FileWriter(parameter.file);
		Json json = new Json();
		json.toJson(data, fileWriter);
		//System.out.println(json.prettyPrint(data));
	}
	
	@Override
	public ParticleEffect loadSync (AssetManager manager, String fileName, FileHandle file, ParticleEffectLoadParameter parameter) {
		ResourceData<ParticleEffect> effectData = null;
		synchronized(items) {
			for(int i=0; i < items.size; ++i){
				ObjectMap.Entry<String, ResourceData<ParticleEffect>> entry = items.get(i);
				if(entry.key.equals(fileName)){
					effectData = entry.value;
					items.removeIndex(i);
					break;
				}
			}
		}

		effectData.resource.load(manager, effectData);
		if(parameter != null){
			if(parameter.batches != null){
				for(ParticleBatch<?> batch : parameter.batches){
					batch.load(manager, effectData);
				}
			}
			effectData.resource.setBatch(parameter.batches);	
		}
		return effectData.resource;
	}
	
	private <T> T find(Array<?> array, Class<T> type){
		for(Object object : array){
			if(type.isAssignableFrom(object.getClass()))
				return (T)object;
		}
		return null;
	}
	
	public static class ParticleEffectLoadParameter extends AssetLoaderParameters<ParticleEffect> {
		Array<ParticleBatch<?>> batches;
		
		public ParticleEffectLoadParameter(Array<ParticleBatch<?>>batches){
			this.batches = batches;
		}
	}
	
	public static class ParticleEffectSaveParameter extends AssetLoaderParameters<ParticleEffect> {
		/**Optional parameters, but should be present to correctly load the settings*/
		Array<ParticleBatch<?>> batches;
		
		/** Required parameters */
		File file;
		AssetManager manager;
		public ParticleEffectSaveParameter(File file, AssetManager manager, Array<ParticleBatch<?>> batches){
			this.batches = batches;
			this.file = file;
			this.manager = manager;
		}
	}
	
}
