package com.badlogic.gdx.tests.g3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.SpawnEllipseSide;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.emitters.RegularEmitter;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ScaleInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.SpawnInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Single;
import com.badlogic.gdx.graphics.g3d.particles.renderers.BillboardRenderer;
import com.badlogic.gdx.graphics.g3d.particles.values.PointSpawnShapeValue;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

/** @author Inferno */
public class ParticleControllerTest extends BaseG3dTest{
	public static final String DEFAULT_PARTICLE = "data/pre_particle.png",
										DEFAULT_SKIN ="data/uiskin.json";
	Quaternion tmpQuaternion = new Quaternion();
	Matrix4 tmpMatrix = new Matrix4(), tmpMatrix4 = new Matrix4();
	Vector3 tmpVector = new Vector3();
	
	private class RotationAction extends Action{
		private ParticleController emitter;
		Vector3 axis;
		float angle;
		
		public RotationAction (ParticleController emitter, Vector3 axis, float angle) {
			this.emitter = emitter;
			this.axis = axis;
			this.angle = angle;
		}

		@Override
		public boolean act (float delta) {
			emitter.getTransform(tmpMatrix);
			tmpQuaternion.set(axis, angle*delta).toMatrix(tmpMatrix4.val);
			tmpMatrix4.mul(tmpMatrix);
			emitter.setTransform(tmpMatrix4);
			return false;
		}
	}
	
	//Simulation
	Array<ParticleController> emitters;
	
	//Rendering
	Environment environment;
	BillboardParticleBatch billboardParticleBatch;
	
	//UI
	Stage ui;
	Label fpsLabel;
	StringBuilder builder;
	
	@Override
	public void create () {
		super.create();
		emitters = new Array<ParticleController>();
		assets.load(DEFAULT_PARTICLE, Texture.class);
		assets.load(DEFAULT_SKIN, Skin.class);
		loading = true;
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0f, 0f, 0.1f, 1f));
		environment.add(new DirectionalLight().set(1f, 1f, 1f,  0, -0.5f, -1 ));
		billboardParticleBatch = new BillboardParticleBatch();
		billboardParticleBatch.setCamera(cam);
		ui = new Stage();
		builder = new StringBuilder();
	}
	
	@Override
	public void resize (int width, int height) {
		super.resize(width, height);
		ui.getViewport().setWorldSize(width, height);
		ui.getViewport().update(width, height, true);
	}
	
	@Override
	protected void onLoaded () {
		Texture particleTexture = assets.get(DEFAULT_PARTICLE);
		billboardParticleBatch.setTexture(assets.get(DEFAULT_PARTICLE, Texture.class));
		
		//X
		ParticleController controller = createBillboardController(new float[] {1, 0.12156863f, 0.047058824f}, particleTexture);
		controller.init();
		controller.start();
		emitters.add(controller);
		controller.translate(tmpVector.set(5,5,0));
		controller.rotate(Vector3.X, -90);
		ui.addAction(new RotationAction(controller, Vector3.X, 360));
		/*
		//Y
		controller = createBillboardController(new float[] { 0.12156863f, 1, 0.047058824f}, particleTexture);
		controller.init();
		controller.start();
		controller.translate(tmpVector.set(0,5,-5));
		controller.rotate(Vector3.Z, -90);
		ui.addAction(new RotationAction(controller, Vector3.Y, -360));
		emitters.add(controller);
		
		//Z
		controller = createBillboardController(new float[] {0.12156863f, 0.047058824f, 1}, particleTexture);
		controller.init();
		controller.start();
		controller.translate(tmpVector.set(0,5,5));
		controller.rotate(Vector3.Z, -90);
		ui.addAction(new RotationAction(controller, Vector3.Z, -360));		
		emitters.add(controller);
		*/
		setupUI();
	}

	private void setupUI () {
		Skin skin = assets.get(DEFAULT_SKIN);
		Table table = new Table();
		table.setFillParent(true);
		table.top().left().add(new Label("FPS ", skin)).left();
		table.add(fpsLabel = new Label("", skin)).left().expandX().row();
		ui.addActor(table);
	}

	private ParticleController createBillboardController (float[] colors, Texture particleTexture) {
		//Emission
		RegularEmitter emitter = new RegularEmitter();
		emitter.getDuration().setLow(3000);
		emitter.getEmission().setHigh(2900);
		emitter.getLife().setHigh(1000);
		emitter.setMaxParticleCount(3000);

		//Spawn
		PointSpawnShapeValue pointSpawnShapeValue = new PointSpawnShapeValue();		
		pointSpawnShapeValue.xOffsetValue.setLow(0, 1f);
		pointSpawnShapeValue.xOffsetValue.setActive(true);
		pointSpawnShapeValue.yOffsetValue.setLow(0, 1f);
		pointSpawnShapeValue.yOffsetValue.setActive(true);
		pointSpawnShapeValue.zOffsetValue.setLow(0, 1f);
		pointSpawnShapeValue.zOffsetValue.setActive(true);
		SpawnInfluencer spawnSource = new SpawnInfluencer(pointSpawnShapeValue);

		ScaleInfluencer scaleInfluencer = new ScaleInfluencer();
		scaleInfluencer.value.setHigh(1f);

		//Color
		ColorInfluencer.Single colorInfluencer = new ColorInfluencer.Single();
		colorInfluencer.colorValue.setColors(new float[] {colors[0], colors[1], colors[2], 0,0,0});
		colorInfluencer.colorValue.setTimeline(new float[] {0, 1});
		colorInfluencer.alphaValue.setHigh(1);
		colorInfluencer.alphaValue.setTimeline(new float[] {0, 0.5f, 0.8f, 1});
		colorInfluencer.alphaValue.setScaling(new float[] {0, 0.15f, 0.5f, 0});

		//Velocity
		/*
		BillboardVelocityInfluencer velocityInfluencer = new BillboardVelocityInfluencer();
		BillboardBrownianVelocityValue velocityValue = new BillboardBrownianVelocityValue();
		velocityValue.strengthValue.setHigh(5, 10);
		velocityInfluencer.velocities.add(velocityValue);
		*/
		
		return new ParticleController("Billboard Controller", emitter, new BillboardRenderer(billboardParticleBatch),
			new RegionInfluencer.Single(particleTexture),
			spawnSource,
			//velocityInfluencer,
			//scaleInfluencer,
			colorInfluencer
			);
	}

	@Override
	protected void render (ModelBatch batch, Array<ModelInstance> instances) {
		if(emitters.size > 0){
			//Update
			float delta = Gdx.graphics.getDeltaTime();
			builder.delete(0, builder.length());
			builder.append(Gdx.graphics.getFramesPerSecond());
			fpsLabel.setText(builder);
			ui.act(delta);

			billboardParticleBatch.begin();
			for (ParticleController controller : emitters){
				controller.update();
				controller.draw();
			}
			billboardParticleBatch.end();
			batch.render(billboardParticleBatch, environment);
		}
		batch.render(instances, environment);
		ui.draw();
	}
}