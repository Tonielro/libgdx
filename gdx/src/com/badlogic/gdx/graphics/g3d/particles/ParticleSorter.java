package com.badlogic.gdx.graphics.g3d.particles;

import java.util.Comparator;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Sort;

/** This class is used by {@link ParticleBatch} to sort the particles before render them.*/
/** @author Inferno */
public class ParticleSorter<T> {
	static final Vector3 TMP_V1 = new Vector3();
	
	/** older particles will be rendered first */
	public static final Comparator<Particle> COMPARATOR_OLDER  = new Comparator<Particle>() {
		@Override
		public int compare (Particle o1, Particle o2) {
			return o1.lifePercent < o2.lifePercent ? -1 : o1.lifePercent == o2.lifePercent ? 0 : 1;
		}
	};
	
	/** younger particles will be rendered first */
	public static final Comparator<Particle> COMPARATOR_YOUNGER = new Comparator<Particle>() {
		@Override
		public int compare (Particle o1, Particle o2) {
			return o1.lifePercent < o2.lifePercent ? 1 : o1.lifePercent == o2.lifePercent ? 0 : -1;
		}
	};
	
	public static abstract class DistanceParticleSorter<T> extends ParticleSorter<T>{

		/** particles more distant from the camera will be rendered first */
		public static final Comparator<Particle> COMPARATOR_FAR_DISTANCE  = new Comparator<Particle>() {
			@Override
			public int compare (Particle o1, Particle o2) {
				return o1.cameraDistance < o2.cameraDistance ? -1 : o1.cameraDistance == o2.cameraDistance ? 0 : 1;
			}
		};
		
		/** particles nearer to the camera will be rendered first */
		public static final Comparator<Particle> COMPARATOR_NEAR_DISTANCE  = new Comparator<Particle>() {
			@Override
			public int compare (Particle o1, Particle o2) {
				return o1.cameraDistance < o2.cameraDistance ? 1 : o1.cameraDistance == o2.cameraDistance ? 0 : -1;
			}
		};
		
		public DistanceParticleSorter(){
			super(COMPARATOR_FAR_DISTANCE);
		}
		
		public DistanceParticleSorter (Comparator comparator) {
			super(comparator);
		}
		

		@Override
		public void sort(T[] particles, int count){
			calculateDistances(particles, count);
			//qsort((Particle[])particles, 0, count-1);
			sorter.sort(particles, comparator, 0, count);
		}

		protected abstract void calculateDistances (T[] particles, int count);
	}

	public static class BillboardDistanceParticleSorter extends DistanceParticleSorter<BillboardParticle>{
		public BillboardDistanceParticleSorter(){}
		
		public BillboardDistanceParticleSorter(Comparator comparator){
			super(comparator);
		}
		
		@Override
		protected void calculateDistances (BillboardParticle[] particles, int count) {
			float[] val = camera.view.val;
			TMP_V1.set(val[Matrix4.M20], val[Matrix4.M21], val[Matrix4.M22]);
			for(int i=0; i <count; ++i){
				BillboardParticle particle = particles[i];
				particles[i].cameraDistance = TMP_V1.dot(particle.x, particle.y, particle.z);
			}
		}
	}
	
	public static class PointSpriteDistanceParticleSorter extends DistanceParticleSorter<PointSpriteParticle>{
		public PointSpriteDistanceParticleSorter(){}
		
		public PointSpriteDistanceParticleSorter(Comparator comparator){
			super(comparator);
		}
		@Override
		protected void calculateDistances (PointSpriteParticle[] particles, int count) {
			float[] val = camera.view.val;
			TMP_V1.set(val[Matrix4.M20], val[Matrix4.M21], val[Matrix4.M22]);
			for(int i=0; i <count; ++i){
				PointSpriteParticle particle = particles[i];
				particles[i].cameraDistance = TMP_V1.dot(particle.x, particle.y, particle.z);
			}
		}
	}
	
	protected Camera camera;
	protected Sort sorter = Sort.instance();
	protected Comparator comparator;
	
	public ParticleSorter(Comparator comparator){
		this.comparator = comparator;
	}
	
	public void sort(T[] particles, int count){
		sorter.sort(particles, comparator, 0, count);
	}
	
	public void setCamera(Camera camera){
		this.camera = camera;
	}
	
	public void setComparator(Comparator comparator){
		this.comparator = comparator;
	}
	
	/*
	public static void qsort(Particle[] a, int si, int ei){
	    //base case
	    if(ei<=si || si>=ei){}

	    else{ 
	        Particle pivot = a[si]; 
	        int i = si+1; Particle tmp; 

	        //partition array 
	        for(int j = si+1; j<= ei; j++){
	            if(pivot.cameraDistance > a[j].cameraDistance){
	                tmp = a[j]; 
	                a[j] = a[i]; 
	                a[i] = tmp; 

	                i++; 
	            }
	        }

	        //put pivot in right position
	        a[si] = a[i-1]; 
	        a[i-1] = pivot; 

	        //call qsort on right and left sides of pivot
	        qsort(a, si, i-2); 
	        qsort(a, i, ei); 
	    }
	}
	*/

}
