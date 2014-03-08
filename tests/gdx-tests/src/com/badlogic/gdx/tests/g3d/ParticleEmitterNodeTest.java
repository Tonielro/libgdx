package com.badlogic.gdx.tests.g3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.newparticles.ParticleEmitterNode;
import com.badlogic.gdx.graphics.g3d.newparticles.values.ScaledNumericValue;
import com.badlogic.gdx.graphics.g3d.newparticles.values.VelocityValue;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEmitter;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class ParticleEmitterNodeTest extends BaseG3dTest{
	public static final String DEFAULT_PARTICLE = "data/particle.png",
										SHIP_MODEL = "data/g3d/teapot.g3db";
	ParticleEmitter emitter;
	
	@Override
	public void create () {
		super.create();
		assets.load(DEFAULT_PARTICLE, Texture.class);
		assets.load(SHIP_MODEL, Model.class);
		loading = true;
	}
	
	@Override
	protected void onLoaded () {
		Texture particleTexture = assets.get(DEFAULT_PARTICLE);
		Model shipModel = assets.get(SHIP_MODEL);
		emitter = createEmitter(new float[] {1, 0.12156863f, 0.047058824f}, particleTexture);
		ParticleEmitterNode node = new ParticleEmitterNode(emitter);
		node.id = "emitter";
		node.translation.set(3, 2.5f, 0);
		node.scale.set(0.5f, 0.5f, 0.5f);
		shipModel.nodes.add(node);
		ModelInstance instance = new ModelInstance(shipModel);
		instances.add(instance);
		emitter = ((ParticleEmitterNode)instance.getNode("emitter")).emitter;
	}
	
	private ParticleEmitter createEmitter (float[] color, Texture texture) {
		ParticleEmitter emitter = new ParticleEmitter();

		emitter.getDuration().setLow(3000);

		emitter.getEmission().setHigh(100);

		emitter.getLife().setHigh(500, 1000);
		emitter.getLife().setTimeline(new float[] {0, 0.66f, 1});
		emitter.getLife().setScaling(new float[] {1, 1, 0.3f});

		emitter.getScaleValue().setHigh(1);

		emitter.getRotation().setLow(1, 360);
		emitter.getRotation().setHigh(180, 180);
		emitter.getRotation().setTimeline(new float[] {0, 1});
		emitter.getRotation().setScaling(new float[] {0, 1});
		emitter.getRotation().setRelative(true);

		//Velocity
		VelocityValue velocityValue = emitter.getVelocityValue(0); 
		ScaledNumericValue thetaValue = velocityValue.getTheta();
		thetaValue.setHigh(0, 359);
		thetaValue.setActive(true);
		
		ScaledNumericValue phiValue = velocityValue.getPhi();
		phiValue.setHigh(45, 135);
		phiValue.setLow(90);
		phiValue.setTimeline(new float[] {0, 0.5f, 1});
		phiValue.setScaling(new float[] {1, 0, 0});
		phiValue.setActive(true);

		velocityValue.getStrength().setHigh(5, 10);
		velocityValue.getStrength().setActive(true);
		velocityValue.setActive(true);
		
		//Color
		emitter.getTint().setColors(color);
		emitter.getTransparency().setHigh(1, 1);
		emitter.getTransparency().setTimeline(new float[] {0, 0.2f, 0.8f, 1});
		emitter.getTransparency().setScaling(new float[] {0, 1, 0.75f, 0});

		emitter.setMaxParticleCount(200);
		emitter.setContinuous(true);
		emitter.setRegionFromTexture(texture);
		emitter.setAttached(true);
		return emitter;
	}

	Quaternion tmpQuaternion = new Quaternion();
	Matrix4 tmpMatrix = new Matrix4(), tmpMatrix4 = new Matrix4();
	@Override
	protected void render (ModelBatch batch, Array<ModelInstance> instances) {
		if(instances.size > 0)
		{
			//Update
			float delta = Gdx.graphics.getDeltaTime();
			boolean complete = true;
			emitter.update(delta);
			if (!emitter.isComplete()) complete = false;
			if (complete) emitter.start();
			for(ModelInstance instance : instances)
				instance.transform.rotate(Vector3.Y, -360f*delta);
			batch.render(instances);
		}
	}

	@Override
	public boolean needsGL20 () {
		return true;
	}
}
