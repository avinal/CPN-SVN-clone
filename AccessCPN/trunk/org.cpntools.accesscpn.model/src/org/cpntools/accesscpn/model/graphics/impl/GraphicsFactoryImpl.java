/**
 * <copyright> </copyright> $Id$
 */
package org.cpntools.accesscpn.model.graphics.impl;

import java.net.URL;

import org.cpntools.accesscpn.model.graphics.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;


/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class GraphicsFactoryImpl extends EFactoryImpl implements GraphicsFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final GraphicsFactoryImpl eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static GraphicsFactoryImpl init() {
		try {
			GraphicsFactoryImpl theGraphicsFactory = (GraphicsFactoryImpl)EPackage.Registry.INSTANCE.getEFactory("http:///dk/au/daimi/ascoveco/cpn/model/graphics.ecore"); 
			if (theGraphicsFactory != null) {
				return theGraphicsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GraphicsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case GraphicsPackageImpl.ANNOTATION_GRAPHICS: return (EObject)createAnnotationGraphics();
			case GraphicsPackageImpl.ARC_GRAPHICS: return (EObject)createArcGraphics();
			case GraphicsPackageImpl.COORDINATE: return (EObject)createCoordinate();
			case GraphicsPackageImpl.FILL: return (EObject)createFill();
			case GraphicsPackageImpl.FONT: return (EObject)createFont();
			case GraphicsPackageImpl.LINE: return (EObject)createLine();
			case GraphicsPackageImpl.NODE_GRAPHICS: return (EObject)createNodeGraphics();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case GraphicsPackageImpl.ALIGN:
				return createAlignFromString(eDataType, initialValue);
			case GraphicsPackageImpl.SHAPE:
				return createShapeFromString(eDataType, initialValue);
			case GraphicsPackageImpl.STYLE:
				return createStyleFromString(eDataType, initialValue);
			case GraphicsPackageImpl.CSS2_COLOR:
				return createCSS2ColorFromString(eDataType, initialValue);
			case GraphicsPackageImpl.CSS2_FONT_FAMILY:
				return createCSS2FontFamilyFromString(eDataType, initialValue);
			case GraphicsPackageImpl.CSS2_FONT_STYLE:
				return createCSS2FontStyleFromString(eDataType, initialValue);
			case GraphicsPackageImpl.CSS2_FONT_WEIGHT:
				return createCSS2FontWeightFromString(eDataType, initialValue);
			case GraphicsPackageImpl.CSS2_FONT_SIZE:
				return createCSS2FontSizeFromString(eDataType, initialValue);
			case GraphicsPackageImpl.NON_NEGATIVE_DECIMAL:
				return createNonNegativeDecimalFromString(eDataType, initialValue);
			case GraphicsPackageImpl.URL:
				return createURLFromString(eDataType, initialValue);
			case GraphicsPackageImpl.ROTATION:
				return createRotationFromString(eDataType, initialValue);
			case GraphicsPackageImpl.DECORATION:
				return createDecorationFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case GraphicsPackageImpl.ALIGN:
				return convertAlignToString(eDataType, instanceValue);
			case GraphicsPackageImpl.SHAPE:
				return convertShapeToString(eDataType, instanceValue);
			case GraphicsPackageImpl.STYLE:
				return convertStyleToString(eDataType, instanceValue);
			case GraphicsPackageImpl.CSS2_COLOR:
				return convertCSS2ColorToString(eDataType, instanceValue);
			case GraphicsPackageImpl.CSS2_FONT_FAMILY:
				return convertCSS2FontFamilyToString(eDataType, instanceValue);
			case GraphicsPackageImpl.CSS2_FONT_STYLE:
				return convertCSS2FontStyleToString(eDataType, instanceValue);
			case GraphicsPackageImpl.CSS2_FONT_WEIGHT:
				return convertCSS2FontWeightToString(eDataType, instanceValue);
			case GraphicsPackageImpl.CSS2_FONT_SIZE:
				return convertCSS2FontSizeToString(eDataType, instanceValue);
			case GraphicsPackageImpl.NON_NEGATIVE_DECIMAL:
				return convertNonNegativeDecimalToString(eDataType, instanceValue);
			case GraphicsPackageImpl.URL:
				return convertURLToString(eDataType, instanceValue);
			case GraphicsPackageImpl.ROTATION:
				return convertRotationToString(eDataType, instanceValue);
			case GraphicsPackageImpl.DECORATION:
				return convertDecorationToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationGraphics createAnnotationGraphics() {
		AnnotationGraphicsImpl annotationGraphics = new AnnotationGraphicsImpl();
		return annotationGraphics;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ArcGraphics createArcGraphics() {
		ArcGraphicsImpl arcGraphics = new ArcGraphicsImpl();
		return arcGraphics;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Coordinate createCoordinate() {
		CoordinateImpl coordinate = new CoordinateImpl();
		return coordinate;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Fill createFill() {
		FillImpl fill = new FillImpl();
		return fill;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Font createFont() {
		FontImpl font = new FontImpl();
		return font;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Line createLine() {
		LineImpl line = new LineImpl();
		return line;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NodeGraphics createNodeGraphics() {
		NodeGraphicsImpl nodeGraphics = new NodeGraphicsImpl();
		return nodeGraphics;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Align createAlignFromString(EDataType eDataType, String initialValue) {
		Align result = Align.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAlignToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Shape createShapeFromString(EDataType eDataType, String initialValue) {
		Shape result = Shape.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShapeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Style createStyleFromString(EDataType eDataType, String initialValue) {
		Style result = Style.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String createCSS2ColorFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCSS2ColorToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String createCSS2FontFamilyFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCSS2FontFamilyToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String createCSS2FontStyleFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCSS2FontStyleToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String createCSS2FontWeightFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCSS2FontWeightToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String createCSS2FontSizeFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCSS2FontSizeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Double createNonNegativeDecimalFromString(EDataType eDataType, String initialValue) {
		return (Double)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNonNegativeDecimalToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public URL createURLFromString(EDataType eDataType, String initialValue) {
		return (URL)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertURLToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Rotation createRotationFromString(EDataType eDataType, String initialValue) {
		return (Rotation)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRotationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Decoration createDecorationFromString(EDataType eDataType, String initialValue) {
		return (Decoration)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDecorationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicsPackageImpl getGraphicsPackage() {
		return (GraphicsPackageImpl)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GraphicsPackageImpl getPackage() {
		return GraphicsPackageImpl.eINSTANCE;
	}

} // GraphicsFactoryImpl
