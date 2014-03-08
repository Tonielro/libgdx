/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.badlogic.gdx.tools.particleeditor3d;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Writer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.particles.Particle;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.controllers.BillboardParticleController;
import com.badlogic.gdx.graphics.g3d.particles.controllers.ModelInstanceParticleController;
import com.badlogic.gdx.graphics.g3d.particles.controllers.ParticleControllerParticleController;
import com.badlogic.gdx.graphics.g3d.particles.controllers.PointParticleController;
import com.badlogic.gdx.graphics.g3d.particles.emitters.RegularEmitter;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ModelInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.BillboardColorInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.ModelInstanceColorInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ScaleInfluencer.BillboardScaleInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ScaleInfluencer.ModelInstanceScaleInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.SpawnShapeInfluencer.BillboardSpawnSource;
import com.badlogic.gdx.graphics.g3d.particles.influencers.SpawnShapeInfluencer.ModelInstanceSpawnSource;
import com.badlogic.gdx.graphics.g3d.particles.influencers.VelocityInfluencer.BillboardVelocityInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.VelocityInfluencer.ModelInstanceVelocityInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.renderers.BillboardRenderer;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ModelInstanceRenderer;
import com.badlogic.gdx.graphics.g3d.particles.renderers.BillboardRenderer.AlignMode;
import com.badlogic.gdx.graphics.g3d.particles.values.PointSpawnShapeValue;
import com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue;
import com.badlogic.gdx.graphics.g3d.particles.values.VelocityValue;
import com.badlogic.gdx.graphics.g3d.particles.values.VelocityValues.BillboardPolarVelocityValue;
import com.badlogic.gdx.graphics.g3d.particles.values.VelocityValues.BillboardRotationVelocityValue;
import com.badlogic.gdx.graphics.g3d.particles.values.VelocityValues.ModelInstancePolarVelocityValue;
import com.badlogic.gdx.graphics.g3d.particles.values.VelocityValues.ModelInstanceRotationVelocityValue;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StreamUtils;

class EffectPanel extends JPanel {
	
	private enum ControllerType{
		Billboard(BillboardParticleController.class, "Billboard"),
		ModelInstance(ModelInstanceParticleController.class, "Model Instance"),
		ParticleController(ParticleControllerParticleController.class, "Particle Controller"),
		Point(PointParticleController.class, "Point");
		
		public Class type;
		public String desc;
		ControllerType(Class type, String desc){
			this.type = type;
			this.desc = desc;
		}
		
		@Override
		public String toString () {
			return desc;
		}
	}
	
	ParticleEditor3D editor;
	JTable emitterTable;
	DefaultTableModel emitterTableModel;
	int editIndex = -1;
	String lastDir;
	JComboBox<ControllerType> controllerTypeCombo;
	
	
	public EffectPanel (ParticleEditor3D editor) {
		this.editor = editor;
		initializeComponents();
	}

	public <T extends ParticleController> T createDefaultEmitter (Class<T> type, boolean select) {

		T controller = null;
		if(type == BillboardParticleController.class){
			controller = (T)createDefaultBillboardController();
		}
		else if(type == ModelInstanceParticleController.class){
			controller = (T) createDefaultModelInstanceController();
		}
		
		controller.init();
		addEmitter(controller, select);
		return controller;
	}

	private ModelInstanceParticleController createDefaultModelInstanceController () {
		//Emission
		RegularEmitter emitter = new RegularEmitter();
		emitter.getDuration().setLow(3000);
		emitter.getEmission().setHigh(80);
		emitter.getLife().setHigh(500, 1000);
		emitter.getLife().setTimeline(new float[] {0, 0.66f, 1});
		emitter.getLife().setScaling(new float[] {1, 1, 0.3f});
		emitter.setMaxParticleCount(100);

		//Renderer
		ModelInstanceRenderer renderer = new ModelInstanceRenderer();
		
		//Scale
		ModelInstanceScaleInfluencer scaleInfluencer = new ModelInstanceScaleInfluencer();
		scaleInfluencer.scaleValue.setHigh(1);

		//Color
		ModelInstanceColorInfluencer colorInfluencer = new ModelInstanceColorInfluencer();
		colorInfluencer.colorValue.setColors(new float[] {1, 0.12156863f, 0.047058824f});
		colorInfluencer.alphaValue.setHigh(1);
		colorInfluencer.alphaValue.setTimeline(new float[] {0, 0.2f, 0.8f, 1});
		colorInfluencer.alphaValue.setScaling(new float[] {0, 1, 0.75f, 0});

		//Spawn
		PointSpawnShapeValue pointSpawnShapeValue = new PointSpawnShapeValue();
		ModelInstanceSpawnSource spawnSource = new ModelInstanceSpawnSource(pointSpawnShapeValue);

		//Velocity
		ModelInstanceVelocityInfluencer velocityInfluencer = new ModelInstanceVelocityInfluencer();

		//Directional
		ModelInstancePolarVelocityValue velocityValue = new ModelInstancePolarVelocityValue();
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
		velocityInfluencer.velocities.add(velocityValue);

		//Rotational
		ModelInstanceRotationVelocityValue rotationVelocityValue = new ModelInstanceRotationVelocityValue();
		rotationVelocityValue.strengthValue.setLow(0, 360);
		rotationVelocityValue.strengthValue.setHigh(180);
		rotationVelocityValue.strengthValue.setTimeline(new float[] {0, 1});
		rotationVelocityValue.strengthValue.setScaling(new float[] {0, 1});
		rotationVelocityValue.strengthValue.setRelative(true);
		velocityInfluencer.velocities.add(rotationVelocityValue);
		
		return new ModelInstanceParticleController("Untitled", emitter, renderer, 
			new ModelInfluencer((Model) editor.assetManager.get(ParticleEditor3D.DEFAULT_MODEL_PARTICLE) ),
			spawnSource,
			scaleInfluencer,
			colorInfluencer,
			velocityInfluencer
			);
	}

	private BillboardParticleController createDefaultBillboardController () {
		//Emission
		RegularEmitter emitter = new RegularEmitter();
		emitter.getDuration().setLow(3000);
		emitter.getEmission().setHigh(250);
		emitter.getLife().setHigh(500, 1000);
		emitter.getLife().setTimeline(new float[] {0, 0.66f, 1});
		emitter.getLife().setScaling(new float[] {1, 1, 0.3f});
		emitter.setMaxParticleCount(200);

		//Renderer
		BillboardRenderer renderer = new BillboardRenderer(AlignMode.Screen, true);
		renderer.setAdditive(true);
		renderer.setCamera(editor.worldCamera);
		
		//Scale
		BillboardScaleInfluencer scaleInfluencer = new BillboardScaleInfluencer();
		scaleInfluencer.scaleValue.setHigh(1);

		//Color
		BillboardColorInfluencer colorInfluencer = new BillboardColorInfluencer();
		colorInfluencer.colorValue.setColors(new float[] {1, 0.12156863f, 0.047058824f});
		colorInfluencer.alphaValue.setHigh(1);
		colorInfluencer.alphaValue.setTimeline(new float[] {0, 0.2f, 0.8f, 1});
		colorInfluencer.alphaValue.setScaling(new float[] {0, 1, 0.75f, 0});

		//Spawn
		PointSpawnShapeValue pointSpawnShapeValue = new PointSpawnShapeValue();
		BillboardSpawnSource spawnSource = new BillboardSpawnSource(pointSpawnShapeValue);

		//Velocity
		BillboardVelocityInfluencer velocityInfluencer = new BillboardVelocityInfluencer();

		//Directional
		BillboardPolarVelocityValue velocityValue = new BillboardPolarVelocityValue();
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
		velocityInfluencer.velocities.add(velocityValue);

		//Rotational
		BillboardRotationVelocityValue rotationVelocityValue = new BillboardRotationVelocityValue();
		rotationVelocityValue.strengthValue.setLow(0, 360);
		rotationVelocityValue.strengthValue.setHigh(180);
		rotationVelocityValue.strengthValue.setTimeline(new float[] {0, 1});
		rotationVelocityValue.strengthValue.setScaling(new float[] {0, 1});
		rotationVelocityValue.strengthValue.setRelative(true);
		velocityInfluencer.velocities.add(rotationVelocityValue);
		
		return new BillboardParticleController("Untitled", emitter, renderer, 
			new RegionInfluencer((Texture) editor.assetManager.get(ParticleEditor3D.DEFAULT_BILLBOARD_PARTICLE) ),
			spawnSource,
			scaleInfluencer,
			colorInfluencer,
			velocityInfluencer
			);
	}

	private void addEmitter (final ParticleController emitter, boolean select) {
		Array<ParticleController> emitters = editor.effect.getControllers();
		emitters.add(emitter);
		emitterTableModel.addRow(new Object[] {emitter.name, true});
		
		int row = emitterTableModel.getRowCount() - 1;
		emitterChecked (row, true); 
		
		if (select) {
			emitterTable.getSelectionModel().setSelectionInterval(row, row);
		}
	}

	void emitterSelected () {
		int row = emitterTable.getSelectedRow();
		if (row == editIndex) 
			return;
		
		editIndex = row;
		editor.reloadRows();
	}

	void emitterChecked (int index, boolean checked) {
		editor.setEnabled(editor.effect.getControllers().get(index), checked);
		editor.effect.start();
	}
	
	void openEffect () {
		/*
		FileDialog dialog = new FileDialog(editor, "Open Effect", FileDialog.LOAD);
		if (lastDir != null) dialog.setDirectory(lastDir);
		dialog.setVisible(true);
		final String file = dialog.getFile();
		final String dir = dialog.getDirectory();
		if (dir == null || file == null || file.trim().length() == 0) return;
		lastDir = dir;
		ParticleEffect effect = new ParticleEffect();
		try {
			effect.loadEmitters(Gdx.files.absolute(new File(dir, file).getAbsolutePath()));
			editor.effect = effect;
			emitterTableModel.getDataVector().removeAllElements();
			editor.particleData.clear();
		} catch (Exception ex) {
			System.out.println("Error loading effect: " + new File(dir, file).getAbsolutePath());
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editor, "Error opening effect.");
			return;
		}
		for (ParticleEmitter emitter : effect.getEmitters()) {
			//emitter.setPosition(0,0,0);
			emitterTableModel.addRow(new Object[] {emitter.getName(), true});
		}
		editIndex = 0;
		emitterTable.getSelectionModel().setSelectionInterval(editIndex, editIndex);
		editor.reloadRows();
		*/
	}

	void saveEffect () {
		/*
		FileDialog dialog = new FileDialog(editor, "Save Effect", FileDialog.SAVE);
		if (lastDir != null) dialog.setDirectory(lastDir);
		dialog.setVisible(true);
		String file = dialog.getFile();
		String dir = dialog.getDirectory();
		if (dir == null || file == null || file.trim().length() == 0) return;
		lastDir = dir;
		int index = 0;
		for (ParticleEmitter emitter : editor.effect.getEmitters())
			emitter.setName((String)emitterTableModel.getValueAt(index++, 0));

		File outputFile = new File(dir, file);
		Writer fileWriter = null;
		try {
			//fileWriter = new FileWriter(outputFile);
			editor.effect.save(outputFile);
		} catch (Exception ex) {
			System.out.println("Error saving effect: " + outputFile.getAbsolutePath());
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editor, "Error saving effect.");
		} finally {
			StreamUtils.closeQuietly(fileWriter);
		}
		*/
	}

	void deleteEmitter () {
		int row = emitterTable.getSelectedRow();
		if (row == -1) return;
		
		int newIndex = Math.min(editIndex, emitterTableModel.getRowCount()-2);
		
		editor.effect.getControllers().removeIndex(row).dispose();
		emitterTableModel.removeRow(row);

		//Reload data check
		emitterTable.getSelectionModel().setSelectionInterval(newIndex, newIndex);
	}

	void move (int direction) {
		if (direction < 0 && editIndex == 0) return;
		Array<ParticleController> emitters = editor.effect.getControllers();
		if (direction > 0 && editIndex == emitters.size - 1) return;
		int insertIndex = editIndex + direction;
		Object name = emitterTableModel.getValueAt(editIndex, 0);
		emitterTableModel.removeRow(editIndex);
		ParticleController emitter = emitters.removeIndex(editIndex);
		emitterTableModel.insertRow(insertIndex, new Object[] {name});
		emitters.insert(insertIndex, emitter);
		editIndex = insertIndex;
		emitterTable.getSelectionModel().setSelectionInterval(editIndex, editIndex);
	}

	private void initializeComponents () {
		setLayout(new GridBagLayout());
		{
			JPanel sideButtons = new JPanel(new GridBagLayout());
			add(sideButtons, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
			{
				controllerTypeCombo = new JComboBox();
				controllerTypeCombo.setModel(new DefaultComboBoxModel(ControllerType.values()));
				sideButtons.add(controllerTypeCombo, new GridBagConstraints(0, -1, 1, 1, 0, 0, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 6, 0), 0, 0));
			}
			{
				JButton newButton = new JButton("New");
				sideButtons.add(newButton, new GridBagConstraints(0, -1, 1, 1, 0, 0, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 6, 0), 0, 0));
				newButton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent event) {
						ControllerType item = (ControllerType)controllerTypeCombo.getSelectedItem();
						createDefaultEmitter(item.type, true);
					}
				});
			}
			{
				JButton deleteButton = new JButton("Delete");
				sideButtons.add(deleteButton, new GridBagConstraints(0, -1, 1, 1, 0, 0, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 6, 0), 0, 0));
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent event) {
						deleteEmitter();
					}
				});
			}
			{
				sideButtons.add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, -1, 1, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 6, 0), 0, 0));
			}
			{
				JButton saveButton = new JButton("Save");
				sideButtons.add(saveButton, new GridBagConstraints(0, -1, 1, 1, 0, 0, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 6, 0), 0, 0));
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent event) {
						saveEffect();
					}
				});
			}
			{
				JButton openButton = new JButton("Open");
				sideButtons.add(openButton, new GridBagConstraints(0, -1, 1, 1, 0, 0, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 6, 0), 0, 0));
				openButton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent event) {
						openEffect();
					}
				});
			}
			{
				JButton upButton = new JButton("Up");
				sideButtons.add(upButton, new GridBagConstraints(0, -1, 1, 1, 0, 1, GridBagConstraints.SOUTH,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 6, 0), 0, 0));
				upButton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent event) {
						move(-1);
					}
				});
			}
			{
				JButton downButton = new JButton("Down");
				sideButtons.add(downButton, new GridBagConstraints(0, -1, 1, 1, 0, 0, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				downButton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent event) {
						move(1);
					}
				});
			}
		}
		{
			JScrollPane scroll = new JScrollPane();
			add(scroll, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,
				0, 0, 6), 0, 0));
			{
				emitterTable = new JTable() {
					public Class getColumnClass (int column) {
						return column == 1 ? Boolean.class : super.getColumnClass(column);
					}
				};
				emitterTable.getTableHeader().setReorderingAllowed(false);
				emitterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scroll.setViewportView(emitterTable);
				emitterTableModel = new DefaultTableModel(new String[0][0], new String[] {"Emitter", ""});
				emitterTable.setModel(emitterTableModel);
				emitterTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					public void valueChanged (ListSelectionEvent event) {
						if (event.getValueIsAdjusting()) return;
						emitterSelected();
					}
				});
				emitterTableModel.addTableModelListener(new TableModelListener() {
					public void tableChanged (TableModelEvent event) {
						if (event.getColumn() != 1) return;
						emitterChecked(event.getFirstRow(), (Boolean)emitterTable.getValueAt(event.getFirstRow(), 1));
					}
				});
			}
		}
	}
}
