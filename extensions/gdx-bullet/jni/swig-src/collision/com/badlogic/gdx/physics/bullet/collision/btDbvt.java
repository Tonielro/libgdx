/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.physics.bullet.BulletBase;
import com.badlogic.gdx.physics.bullet.linearmath.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

public class btDbvt extends BulletBase {
	private long swigCPtr;
	
	protected btDbvt(final String className, long cPtr, boolean cMemoryOwn) {
		super(className, cPtr, cMemoryOwn);
		swigCPtr = cPtr;
	}
	
	/** Construct a new btDbvt, normally you should not need this constructor it's intended for low-level usage. */ 
	public btDbvt(long cPtr, boolean cMemoryOwn) {
		this("btDbvt", cPtr, cMemoryOwn);
		construct();
	}
	
	@Override
	protected void reset(long cPtr, boolean cMemoryOwn) {
		if (!destroyed)
			destroy();
		super.reset(swigCPtr = cPtr, cMemoryOwn);
	}
	
	public static long getCPtr(btDbvt obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	@Override
	protected void finalize() throws Throwable {
		if (!destroyed)
			destroy();
		super.finalize();
	}

  @Override protected synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				CollisionJNI.delete_btDbvt(swigCPtr);
			}
			swigCPtr = 0;
		}
		super.delete();
	}

  static public class sStkNN extends BulletBase {
  	private long swigCPtr;
  	
  	protected sStkNN(final String className, long cPtr, boolean cMemoryOwn) {
  		super(className, cPtr, cMemoryOwn);
  		swigCPtr = cPtr;
  	}
  	
  	/** Construct a new sStkNN, normally you should not need this constructor it's intended for low-level usage. */ 
  	public sStkNN(long cPtr, boolean cMemoryOwn) {
  		this("sStkNN", cPtr, cMemoryOwn);
  		construct();
  	}
  	
  	@Override
  	protected void reset(long cPtr, boolean cMemoryOwn) {
  		if (!destroyed)
  			destroy();
  		super.reset(swigCPtr = cPtr, cMemoryOwn);
  	}
  	
  	public static long getCPtr(sStkNN obj) {
  		return (obj == null) ? 0 : obj.swigCPtr;
  	}
  
  	@Override
  	protected void finalize() throws Throwable {
  		if (!destroyed)
  			destroy();
  		super.finalize();
  	}
  
    @Override protected synchronized void delete() {
  		if (swigCPtr != 0) {
  			if (swigCMemOwn) {
  				swigCMemOwn = false;
  				CollisionJNI.delete_btDbvt_sStkNN(swigCPtr);
  			}
  			swigCPtr = 0;
  		}
  		super.delete();
  	}
  
    public void setA(btDbvtNode value) {
      CollisionJNI.btDbvt_sStkNN_a_set(swigCPtr, this, btDbvtNode.getCPtr(value), value);
    }
  
    public btDbvtNode getA() {
      long cPtr = CollisionJNI.btDbvt_sStkNN_a_get(swigCPtr, this);
      return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
    }
  
    public void setB(btDbvtNode value) {
      CollisionJNI.btDbvt_sStkNN_b_set(swigCPtr, this, btDbvtNode.getCPtr(value), value);
    }
  
    public btDbvtNode getB() {
      long cPtr = CollisionJNI.btDbvt_sStkNN_b_get(swigCPtr, this);
      return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
    }
  
    public sStkNN() {
      this(CollisionJNI.new_btDbvt_sStkNN__SWIG_0(), true);
    }
  
    public sStkNN(btDbvtNode na, btDbvtNode nb) {
      this(CollisionJNI.new_btDbvt_sStkNN__SWIG_1(btDbvtNode.getCPtr(na), na, btDbvtNode.getCPtr(nb), nb), true);
    }
  
  }

  static public class sStkNP extends BulletBase {
  	private long swigCPtr;
  	
  	protected sStkNP(final String className, long cPtr, boolean cMemoryOwn) {
  		super(className, cPtr, cMemoryOwn);
  		swigCPtr = cPtr;
  	}
  	
  	/** Construct a new sStkNP, normally you should not need this constructor it's intended for low-level usage. */ 
  	public sStkNP(long cPtr, boolean cMemoryOwn) {
  		this("sStkNP", cPtr, cMemoryOwn);
  		construct();
  	}
  	
  	@Override
  	protected void reset(long cPtr, boolean cMemoryOwn) {
  		if (!destroyed)
  			destroy();
  		super.reset(swigCPtr = cPtr, cMemoryOwn);
  	}
  	
  	public static long getCPtr(sStkNP obj) {
  		return (obj == null) ? 0 : obj.swigCPtr;
  	}
  
  	@Override
  	protected void finalize() throws Throwable {
  		if (!destroyed)
  			destroy();
  		super.finalize();
  	}
  
    @Override protected synchronized void delete() {
  		if (swigCPtr != 0) {
  			if (swigCMemOwn) {
  				swigCMemOwn = false;
  				CollisionJNI.delete_btDbvt_sStkNP(swigCPtr);
  			}
  			swigCPtr = 0;
  		}
  		super.delete();
  	}
  
    public void setNode(btDbvtNode value) {
      CollisionJNI.btDbvt_sStkNP_node_set(swigCPtr, this, btDbvtNode.getCPtr(value), value);
    }
  
    public btDbvtNode getNode() {
      long cPtr = CollisionJNI.btDbvt_sStkNP_node_get(swigCPtr, this);
      return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
    }
  
    public void setMask(int value) {
      CollisionJNI.btDbvt_sStkNP_mask_set(swigCPtr, this, value);
    }
  
    public int getMask() {
      return CollisionJNI.btDbvt_sStkNP_mask_get(swigCPtr, this);
    }
  
    public sStkNP(btDbvtNode n, long m) {
      this(CollisionJNI.new_btDbvt_sStkNP(btDbvtNode.getCPtr(n), n, m), true);
    }
  
  }

  static public class sStkNPS extends BulletBase {
  	private long swigCPtr;
  	
  	protected sStkNPS(final String className, long cPtr, boolean cMemoryOwn) {
  		super(className, cPtr, cMemoryOwn);
  		swigCPtr = cPtr;
  	}
  	
  	/** Construct a new sStkNPS, normally you should not need this constructor it's intended for low-level usage. */ 
  	public sStkNPS(long cPtr, boolean cMemoryOwn) {
  		this("sStkNPS", cPtr, cMemoryOwn);
  		construct();
  	}
  	
  	@Override
  	protected void reset(long cPtr, boolean cMemoryOwn) {
  		if (!destroyed)
  			destroy();
  		super.reset(swigCPtr = cPtr, cMemoryOwn);
  	}
  	
  	public static long getCPtr(sStkNPS obj) {
  		return (obj == null) ? 0 : obj.swigCPtr;
  	}
  
  	@Override
  	protected void finalize() throws Throwable {
  		if (!destroyed)
  			destroy();
  		super.finalize();
  	}
  
    @Override protected synchronized void delete() {
  		if (swigCPtr != 0) {
  			if (swigCMemOwn) {
  				swigCMemOwn = false;
  				CollisionJNI.delete_btDbvt_sStkNPS(swigCPtr);
  			}
  			swigCPtr = 0;
  		}
  		super.delete();
  	}
  
    public void setNode(btDbvtNode value) {
      CollisionJNI.btDbvt_sStkNPS_node_set(swigCPtr, this, btDbvtNode.getCPtr(value), value);
    }
  
    public btDbvtNode getNode() {
      long cPtr = CollisionJNI.btDbvt_sStkNPS_node_get(swigCPtr, this);
      return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
    }
  
    public void setMask(int value) {
      CollisionJNI.btDbvt_sStkNPS_mask_set(swigCPtr, this, value);
    }
  
    public int getMask() {
      return CollisionJNI.btDbvt_sStkNPS_mask_get(swigCPtr, this);
    }
  
    public void setValue(float value) {
      CollisionJNI.btDbvt_sStkNPS_value_set(swigCPtr, this, value);
    }
  
    public float getValue() {
      return CollisionJNI.btDbvt_sStkNPS_value_get(swigCPtr, this);
    }
  
    public sStkNPS() {
      this(CollisionJNI.new_btDbvt_sStkNPS__SWIG_0(), true);
    }
  
    public sStkNPS(btDbvtNode n, long m, float v) {
      this(CollisionJNI.new_btDbvt_sStkNPS__SWIG_1(btDbvtNode.getCPtr(n), n, m, v), true);
    }
  
  }

  static public class sStkCLN extends BulletBase {
  	private long swigCPtr;
  	
  	protected sStkCLN(final String className, long cPtr, boolean cMemoryOwn) {
  		super(className, cPtr, cMemoryOwn);
  		swigCPtr = cPtr;
  	}
  	
  	/** Construct a new sStkCLN, normally you should not need this constructor it's intended for low-level usage. */ 
  	public sStkCLN(long cPtr, boolean cMemoryOwn) {
  		this("sStkCLN", cPtr, cMemoryOwn);
  		construct();
  	}
  	
  	@Override
  	protected void reset(long cPtr, boolean cMemoryOwn) {
  		if (!destroyed)
  			destroy();
  		super.reset(swigCPtr = cPtr, cMemoryOwn);
  	}
  	
  	public static long getCPtr(sStkCLN obj) {
  		return (obj == null) ? 0 : obj.swigCPtr;
  	}
  
  	@Override
  	protected void finalize() throws Throwable {
  		if (!destroyed)
  			destroy();
  		super.finalize();
  	}
  
    @Override protected synchronized void delete() {
  		if (swigCPtr != 0) {
  			if (swigCMemOwn) {
  				swigCMemOwn = false;
  				CollisionJNI.delete_btDbvt_sStkCLN(swigCPtr);
  			}
  			swigCPtr = 0;
  		}
  		super.delete();
  	}
  
    public void setNode(btDbvtNode value) {
      CollisionJNI.btDbvt_sStkCLN_node_set(swigCPtr, this, btDbvtNode.getCPtr(value), value);
    }
  
    public btDbvtNode getNode() {
      long cPtr = CollisionJNI.btDbvt_sStkCLN_node_get(swigCPtr, this);
      return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
    }
  
    public void setParent(btDbvtNode value) {
      CollisionJNI.btDbvt_sStkCLN_parent_set(swigCPtr, this, btDbvtNode.getCPtr(value), value);
    }
  
    public btDbvtNode getParent() {
      long cPtr = CollisionJNI.btDbvt_sStkCLN_parent_get(swigCPtr, this);
      return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
    }
  
    public sStkCLN(btDbvtNode n, btDbvtNode p) {
      this(CollisionJNI.new_btDbvt_sStkCLN(btDbvtNode.getCPtr(n), n, btDbvtNode.getCPtr(p), p), true);
    }
  
  }

  static public class ICollide extends BulletBase {
  	private long swigCPtr;
  	
  	protected ICollide(final String className, long cPtr, boolean cMemoryOwn) {
  		super(className, cPtr, cMemoryOwn);
  		swigCPtr = cPtr;
  	}
  	
  	/** Construct a new ICollide, normally you should not need this constructor it's intended for low-level usage. */ 
  	public ICollide(long cPtr, boolean cMemoryOwn) {
  		this("ICollide", cPtr, cMemoryOwn);
  		construct();
  	}
  	
  	@Override
  	protected void reset(long cPtr, boolean cMemoryOwn) {
  		if (!destroyed)
  			destroy();
  		super.reset(swigCPtr = cPtr, cMemoryOwn);
  	}
  	
  	public static long getCPtr(ICollide obj) {
  		return (obj == null) ? 0 : obj.swigCPtr;
  	}
  
  	@Override
  	protected void finalize() throws Throwable {
  		if (!destroyed)
  			destroy();
  		super.finalize();
  	}
  
    @Override protected synchronized void delete() {
  		if (swigCPtr != 0) {
  			if (swigCMemOwn) {
  				swigCMemOwn = false;
  				CollisionJNI.delete_btDbvt_ICollide(swigCPtr);
  			}
  			swigCPtr = 0;
  		}
  		super.delete();
  	}
  
    public void Process(btDbvtNode arg0, btDbvtNode arg1) {
      CollisionJNI.btDbvt_ICollide_Process__SWIG_0(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0, btDbvtNode.getCPtr(arg1), arg1);
    }
  
    public void Process(btDbvtNode arg0) {
      CollisionJNI.btDbvt_ICollide_Process__SWIG_1(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0);
    }
  
    public void Process(btDbvtNode n, float arg1) {
      CollisionJNI.btDbvt_ICollide_Process__SWIG_2(swigCPtr, this, btDbvtNode.getCPtr(n), n, arg1);
    }
  
    public boolean Descent(btDbvtNode arg0) {
      return CollisionJNI.btDbvt_ICollide_Descent(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0);
    }
  
    public boolean AllLeaves(btDbvtNode arg0) {
      return CollisionJNI.btDbvt_ICollide_AllLeaves(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0);
    }
  
    public ICollide() {
      this(CollisionJNI.new_btDbvt_ICollide(), true);
    }
  
  }

  static public class IWriter extends BulletBase {
  	private long swigCPtr;
  	
  	protected IWriter(final String className, long cPtr, boolean cMemoryOwn) {
  		super(className, cPtr, cMemoryOwn);
  		swigCPtr = cPtr;
  	}
  	
  	/** Construct a new IWriter, normally you should not need this constructor it's intended for low-level usage. */ 
  	public IWriter(long cPtr, boolean cMemoryOwn) {
  		this("IWriter", cPtr, cMemoryOwn);
  		construct();
  	}
  	
  	@Override
  	protected void reset(long cPtr, boolean cMemoryOwn) {
  		if (!destroyed)
  			destroy();
  		super.reset(swigCPtr = cPtr, cMemoryOwn);
  	}
  	
  	public static long getCPtr(IWriter obj) {
  		return (obj == null) ? 0 : obj.swigCPtr;
  	}
  
  	@Override
  	protected void finalize() throws Throwable {
  		if (!destroyed)
  			destroy();
  		super.finalize();
  	}
  
    @Override protected synchronized void delete() {
  		if (swigCPtr != 0) {
  			if (swigCMemOwn) {
  				swigCMemOwn = false;
  				CollisionJNI.delete_btDbvt_IWriter(swigCPtr);
  			}
  			swigCPtr = 0;
  		}
  		super.delete();
  	}
  
    public void Prepare(btDbvtNode root, int numnodes) {
      CollisionJNI.btDbvt_IWriter_Prepare(swigCPtr, this, btDbvtNode.getCPtr(root), root, numnodes);
    }
  
    public void WriteNode(btDbvtNode arg0, int index, int parent, int child0, int child1) {
      CollisionJNI.btDbvt_IWriter_WriteNode(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0, index, parent, child0, child1);
    }
  
    public void WriteLeaf(btDbvtNode arg0, int index, int parent) {
      CollisionJNI.btDbvt_IWriter_WriteLeaf(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0, index, parent);
    }
  
  }

  static public class IClone extends BulletBase {
  	private long swigCPtr;
  	
  	protected IClone(final String className, long cPtr, boolean cMemoryOwn) {
  		super(className, cPtr, cMemoryOwn);
  		swigCPtr = cPtr;
  	}
  	
  	/** Construct a new IClone, normally you should not need this constructor it's intended for low-level usage. */ 
  	public IClone(long cPtr, boolean cMemoryOwn) {
  		this("IClone", cPtr, cMemoryOwn);
  		construct();
  	}
  	
  	@Override
  	protected void reset(long cPtr, boolean cMemoryOwn) {
  		if (!destroyed)
  			destroy();
  		super.reset(swigCPtr = cPtr, cMemoryOwn);
  	}
  	
  	public static long getCPtr(IClone obj) {
  		return (obj == null) ? 0 : obj.swigCPtr;
  	}
  
  	@Override
  	protected void finalize() throws Throwable {
  		if (!destroyed)
  			destroy();
  		super.finalize();
  	}
  
    @Override protected synchronized void delete() {
  		if (swigCPtr != 0) {
  			if (swigCMemOwn) {
  				swigCMemOwn = false;
  				CollisionJNI.delete_btDbvt_IClone(swigCPtr);
  			}
  			swigCPtr = 0;
  		}
  		super.delete();
  	}
  
    public void CloneLeaf(btDbvtNode arg0) {
      CollisionJNI.btDbvt_IClone_CloneLeaf(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0);
    }
  
    public IClone() {
      this(CollisionJNI.new_btDbvt_IClone(), true);
    }
  
  }

  public void setRoot(btDbvtNode value) {
    CollisionJNI.btDbvt_root_set(swigCPtr, this, btDbvtNode.getCPtr(value), value);
  }

  public btDbvtNode getRoot() {
    long cPtr = CollisionJNI.btDbvt_root_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
  }

  public void setFree(btDbvtNode value) {
    CollisionJNI.btDbvt_free_set(swigCPtr, this, btDbvtNode.getCPtr(value), value);
  }

  public btDbvtNode getFree() {
    long cPtr = CollisionJNI.btDbvt_free_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
  }

  public void setLkhd(int value) {
    CollisionJNI.btDbvt_lkhd_set(swigCPtr, this, value);
  }

  public int getLkhd() {
    return CollisionJNI.btDbvt_lkhd_get(swigCPtr, this);
  }

  public void setLeaves(int value) {
    CollisionJNI.btDbvt_leaves_set(swigCPtr, this, value);
  }

  public int getLeaves() {
    return CollisionJNI.btDbvt_leaves_get(swigCPtr, this);
  }

  public void setOpath(long value) {
    CollisionJNI.btDbvt_opath_set(swigCPtr, this, value);
  }

  public long getOpath() {
    return CollisionJNI.btDbvt_opath_get(swigCPtr, this);
  }

  public void setStkStack(SWIGTYPE_p_btAlignedObjectArrayT_btDbvt__sStkNN_t value) {
    CollisionJNI.btDbvt_stkStack_set(swigCPtr, this, SWIGTYPE_p_btAlignedObjectArrayT_btDbvt__sStkNN_t.getCPtr(value));
  }

  public SWIGTYPE_p_btAlignedObjectArrayT_btDbvt__sStkNN_t getStkStack() {
    long cPtr = CollisionJNI.btDbvt_stkStack_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_btAlignedObjectArrayT_btDbvt__sStkNN_t(cPtr, false);
  }

  public void setRayTestStack(SWIGTYPE_p_btAlignedObjectArrayT_btDbvtNode_const_p_t value) {
    CollisionJNI.btDbvt_rayTestStack_set(swigCPtr, this, SWIGTYPE_p_btAlignedObjectArrayT_btDbvtNode_const_p_t.getCPtr(value));
  }

  public SWIGTYPE_p_btAlignedObjectArrayT_btDbvtNode_const_p_t getRayTestStack() {
    long cPtr = CollisionJNI.btDbvt_rayTestStack_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_btAlignedObjectArrayT_btDbvtNode_const_p_t(cPtr, false);
  }

  public btDbvt() {
    this(CollisionJNI.new_btDbvt(), true);
  }

  public void clear() {
    CollisionJNI.btDbvt_clear(swigCPtr, this);
  }

  public boolean empty() {
    return CollisionJNI.btDbvt_empty(swigCPtr, this);
  }

  public void optimizeBottomUp() {
    CollisionJNI.btDbvt_optimizeBottomUp(swigCPtr, this);
  }

  public void optimizeTopDown(int bu_treshold) {
    CollisionJNI.btDbvt_optimizeTopDown__SWIG_0(swigCPtr, this, bu_treshold);
  }

  public void optimizeTopDown() {
    CollisionJNI.btDbvt_optimizeTopDown__SWIG_1(swigCPtr, this);
  }

  public void optimizeIncremental(int passes) {
    CollisionJNI.btDbvt_optimizeIncremental(swigCPtr, this, passes);
  }

  public btDbvtNode insert(btDbvtAabbMm box, long data) {
    long cPtr = CollisionJNI.btDbvt_insert(swigCPtr, this, btDbvtAabbMm.getCPtr(box), box, data);
    return (cPtr == 0) ? null : new btDbvtNode(cPtr, false);
  }

  public void update(btDbvtNode leaf, int lookahead) {
    CollisionJNI.btDbvt_update__SWIG_0(swigCPtr, this, btDbvtNode.getCPtr(leaf), leaf, lookahead);
  }

  public void update(btDbvtNode leaf) {
    CollisionJNI.btDbvt_update__SWIG_1(swigCPtr, this, btDbvtNode.getCPtr(leaf), leaf);
  }

  public void update(btDbvtNode leaf, btDbvtAabbMm volume) {
    CollisionJNI.btDbvt_update__SWIG_2(swigCPtr, this, btDbvtNode.getCPtr(leaf), leaf, btDbvtAabbMm.getCPtr(volume), volume);
  }

  public boolean update(btDbvtNode leaf, btDbvtAabbMm volume, Vector3 velocity, float margin) {
    return CollisionJNI.btDbvt_update__SWIG_3(swigCPtr, this, btDbvtNode.getCPtr(leaf), leaf, btDbvtAabbMm.getCPtr(volume), volume, velocity, margin);
  }

  public boolean update(btDbvtNode leaf, btDbvtAabbMm volume, Vector3 velocity) {
    return CollisionJNI.btDbvt_update__SWIG_4(swigCPtr, this, btDbvtNode.getCPtr(leaf), leaf, btDbvtAabbMm.getCPtr(volume), volume, velocity);
  }

  public boolean update(btDbvtNode leaf, btDbvtAabbMm volume, float margin) {
    return CollisionJNI.btDbvt_update__SWIG_5(swigCPtr, this, btDbvtNode.getCPtr(leaf), leaf, btDbvtAabbMm.getCPtr(volume), volume, margin);
  }

  public void remove(btDbvtNode leaf) {
    CollisionJNI.btDbvt_remove(swigCPtr, this, btDbvtNode.getCPtr(leaf), leaf);
  }

  public void write(btDbvt.IWriter iwriter) {
    CollisionJNI.btDbvt_write(swigCPtr, this, btDbvt.IWriter.getCPtr(iwriter), iwriter);
  }

  public void clone(btDbvt dest, btDbvt.IClone iclone) {
    CollisionJNI.btDbvt_clone__SWIG_0(swigCPtr, this, btDbvt.getCPtr(dest), dest, btDbvt.IClone.getCPtr(iclone), iclone);
  }

  public void clone(btDbvt dest) {
    CollisionJNI.btDbvt_clone__SWIG_1(swigCPtr, this, btDbvt.getCPtr(dest), dest);
  }

  public static int maxdepth(btDbvtNode node) {
    return CollisionJNI.btDbvt_maxdepth(btDbvtNode.getCPtr(node), node);
  }

  public static int countLeaves(btDbvtNode node) {
    return CollisionJNI.btDbvt_countLeaves(btDbvtNode.getCPtr(node), node);
  }

  public static void extractLeaves(btDbvtNode node, SWIGTYPE_p_btAlignedObjectArrayT_btDbvtNode_const_p_t leaves) {
    CollisionJNI.btDbvt_extractLeaves(btDbvtNode.getCPtr(node), node, SWIGTYPE_p_btAlignedObjectArrayT_btDbvtNode_const_p_t.getCPtr(leaves));
  }

  public static void benchmark() {
    CollisionJNI.btDbvt_benchmark();
  }

  public static void enumNodes(btDbvtNode root, btDbvt.ICollide policy) {
    CollisionJNI.btDbvt_enumNodes(btDbvtNode.getCPtr(root), root, btDbvt.ICollide.getCPtr(policy), policy);
  }

  public static void enumLeaves(btDbvtNode root, btDbvt.ICollide policy) {
    CollisionJNI.btDbvt_enumLeaves(btDbvtNode.getCPtr(root), root, btDbvt.ICollide.getCPtr(policy), policy);
  }

  public void collideTT(btDbvtNode root0, btDbvtNode root1, btDbvt.ICollide policy) {
    CollisionJNI.btDbvt_collideTT(swigCPtr, this, btDbvtNode.getCPtr(root0), root0, btDbvtNode.getCPtr(root1), root1, btDbvt.ICollide.getCPtr(policy), policy);
  }

  public void collideTTpersistentStack(btDbvtNode root0, btDbvtNode root1, btDbvt.ICollide policy) {
    CollisionJNI.btDbvt_collideTTpersistentStack(swigCPtr, this, btDbvtNode.getCPtr(root0), root0, btDbvtNode.getCPtr(root1), root1, btDbvt.ICollide.getCPtr(policy), policy);
  }

  public void collideTV(btDbvtNode root, btDbvtAabbMm volume, btDbvt.ICollide policy) {
    CollisionJNI.btDbvt_collideTV(swigCPtr, this, btDbvtNode.getCPtr(root), root, btDbvtAabbMm.getCPtr(volume), volume, btDbvt.ICollide.getCPtr(policy), policy);
  }

  public static void rayTest(btDbvtNode root, Vector3 rayFrom, Vector3 rayTo, btDbvt.ICollide policy) {
    CollisionJNI.btDbvt_rayTest(btDbvtNode.getCPtr(root), root, rayFrom, rayTo, btDbvt.ICollide.getCPtr(policy), policy);
  }

  public void rayTestInternal(btDbvtNode root, Vector3 rayFrom, Vector3 rayTo, Vector3 rayDirectionInverse, long[] signs, float lambda_max, Vector3 aabbMin, Vector3 aabbMax, btDbvt.ICollide policy) {
    CollisionJNI.btDbvt_rayTestInternal(swigCPtr, this, btDbvtNode.getCPtr(root), root, rayFrom, rayTo, rayDirectionInverse, signs, lambda_max, aabbMin, aabbMax, btDbvt.ICollide.getCPtr(policy), policy);
  }

  public static void collideKDOP(btDbvtNode root, btVector3 normals, java.nio.FloatBuffer offsets, int count, btDbvt.ICollide policy) {
    assert offsets.isDirect() : "Buffer must be allocated direct.";
    {
      CollisionJNI.btDbvt_collideKDOP(btDbvtNode.getCPtr(root), root, btVector3.getCPtr(normals), normals, offsets, count, btDbvt.ICollide.getCPtr(policy), policy);
    }
  }

  public static void collideOCL(btDbvtNode root, btVector3 normals, java.nio.FloatBuffer offsets, Vector3 sortaxis, int count, btDbvt.ICollide policy, boolean fullsort) {
    assert offsets.isDirect() : "Buffer must be allocated direct.";
    {
      CollisionJNI.btDbvt_collideOCL__SWIG_0(btDbvtNode.getCPtr(root), root, btVector3.getCPtr(normals), normals, offsets, sortaxis, count, btDbvt.ICollide.getCPtr(policy), policy, fullsort);
    }
  }

  public static void collideOCL(btDbvtNode root, btVector3 normals, java.nio.FloatBuffer offsets, Vector3 sortaxis, int count, btDbvt.ICollide policy) {
    assert offsets.isDirect() : "Buffer must be allocated direct.";
    {
      CollisionJNI.btDbvt_collideOCL__SWIG_1(btDbvtNode.getCPtr(root), root, btVector3.getCPtr(normals), normals, offsets, sortaxis, count, btDbvt.ICollide.getCPtr(policy), policy);
    }
  }

  public static void collideTU(btDbvtNode root, btDbvt.ICollide policy) {
    CollisionJNI.btDbvt_collideTU(btDbvtNode.getCPtr(root), root, btDbvt.ICollide.getCPtr(policy), policy);
  }

  public static int nearest(java.nio.IntBuffer i, btDbvt.sStkNPS a, float v, int l, int h) {
    assert i.isDirect() : "Buffer must be allocated direct.";
    {
      return CollisionJNI.btDbvt_nearest(i, btDbvt.sStkNPS.getCPtr(a), a, v, l, h);
    }
  }

  public static int allocate(SWIGTYPE_p_btAlignedObjectArrayT_int_t ifree, SWIGTYPE_p_btAlignedObjectArrayT_btDbvt__sStkNPS_t stock, btDbvt.sStkNPS value) {
    return CollisionJNI.btDbvt_allocate(SWIGTYPE_p_btAlignedObjectArrayT_int_t.getCPtr(ifree), SWIGTYPE_p_btAlignedObjectArrayT_btDbvt__sStkNPS_t.getCPtr(stock), btDbvt.sStkNPS.getCPtr(value), value);
  }

  public final static int SIMPLE_STACKSIZE = 64;
  public final static int DOUBLE_STACKSIZE = SIMPLE_STACKSIZE*2;

}
