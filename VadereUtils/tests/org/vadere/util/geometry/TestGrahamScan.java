package org.vadere.util.geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vadere.util.geometry.shapes.VPoint;
import org.vadere.util.geometry.shapes.VPolygon;

import java.util.List;
import java.util.Stack;

import static  org.junit.jupiter.api.Assertions.assertFalse;
import static  org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic unit test for the {@link org.vadere.util.geometry.GrahamScan} class.
 *
 */
public class TestGrahamScan {

	@BeforeEach
	public void setUp() throws Exception {}

	@Test
	public void testSimpleConvexHull() {
		GrahamScan grahamScan = new GrahamScan(new VPoint[] {
				new VPoint(2.0, 2.0),
				new VPoint(-2.0, -2.0),
				new VPoint(1.0, 2.0),
				new VPoint(2.0, -2.0),
				new VPoint(-2.0, 2.0),
				new VPoint(0.0, 0.0)
		});


		Stack<VPoint> stack = grahamScan.getConvexHull();
		assertTrue(stack.size() == 4, "convex hull triangleContains to many points");
		assertTrue(stack.contains(new VPoint(2.0, 2.0)), "convex hull does not contain " + new VPoint(2.0, 2.0));
		assertTrue(stack.contains(new VPoint(-2.0, -2.0)), "convex hull does not contain " + new VPoint(-2.0, -2.0));
		assertTrue(stack.contains(new VPoint(2.0, -2.0)), "convex hull does not contain " + new VPoint(2.0, -2.0));
		assertTrue(stack.contains(new VPoint(-2.0, 2.0)), "convex hull does not contain " + new VPoint(-2.0, 2.0));

		assertFalse(stack.contains(new VPoint(0.0, 0.0)), "convex hull does contain " + new VPoint(0.0, 0.0));
		assertFalse(stack.contains(new VPoint(1.0, 2.0)), "convex hull does contain " + new VPoint(1.0, 2.0));



	}

	@Test
	public void testPolytopConvexHull() {
		GrahamScan grahamScan = new GrahamScan(new VPoint[] {
				new VPoint(2.0, 2.0),
				new VPoint(-2.0, -2.0),
				new VPoint(2.0, -2.0),
				new VPoint(-2.0, 2.0),
				new VPoint(0.0, 0.0)
		});

		VPolygon polytope = grahamScan.getPolytope();

		assertTrue(polytope.contains(new VPoint(1.9999, 1.999)),
				"polytope does not contain " + new VPoint(1.9999, 1.999));
		assertTrue(polytope.contains(new VPoint(-1.999, -1.999)),
				"polytope does not contain " + new VPoint(-1.999, -1.999));
		assertTrue(polytope.contains(new VPoint(1.999, -1.999)),
				"polytope does not contain " + new VPoint(1.999, -1.999));
		assertTrue(polytope.contains(new VPoint(-1.999, 1.999)),
				"polytope does not contain " + new VPoint(-1.999, 1.999));

		assertFalse(polytope.contains(new VPoint(1.999, 2.001)), "polytope does contain " + new VPoint(1.999, 2.001));
		assertFalse(polytope.contains(new VPoint(2.0001, 2.0)), "polytope does contain " + new VPoint(2.0001, 2.0));
		assertFalse(polytope.contains(new VPoint(-2.001, -2.0)), "polytope does contain " + new VPoint(-2.001, -2.0));
		assertFalse(polytope.contains(new VPoint(-2.0, -2.00001)),
				"polytope does contain " + new VPoint(-2.0, -2.00001));
		assertFalse(polytope.contains(new VPoint(-2.0001, 2.0)), "polytope does contain " + new VPoint(-2.0001, 2.0));
		assertFalse(polytope.contains(new VPoint(-2.0, 2.00001)), "polytope does contain " + new VPoint(-2.0, 2.00001));

		assertTrue(polytope.contains(new VPoint(0.0, 0.0)), "convex hull does contain " + new VPoint(0.0, 0.0));

		List<VPoint> points = polytope.getPath();
		assertTrue(GeometryUtils.isCW(points.get(0), points.get(1), points.get(2)));
	}

	@Test
	public void testOrientation() {
		GrahamScan grahamScan1 = new GrahamScan(new VPoint[] {
				new VPoint(0.0, 0.0),
				new VPoint(1.0, 0.0),
				new VPoint(0.5, 2.0)
		});

		List<VPoint> points = grahamScan1.getPolytope().getPath();
		assertTrue(GeometryUtils.isCW(points.get(0), points.get(1), points.get(2)));

		GrahamScan grahamScan2 = new GrahamScan(new VPoint[] {
				new VPoint(0.0, 0.0),
				new VPoint(0.5, 2.0),
				new VPoint(1.0, 0.0)

		});

		points = grahamScan2.getPolytope().getPath();
		assertTrue(GeometryUtils.isCW(points.get(0), points.get(1), points.get(2)));
	}

	@Test
	public void testThreePointsOnOneLine() {
		GrahamScan grahamScan = new GrahamScan(new VPoint[] {
				new VPoint(2.0, 2.0),
				new VPoint(1.0, 2.0),
				new VPoint(-2.0, 2.0)
		});

		Stack<VPoint> stack = grahamScan.getConvexHull();
		assertTrue(stack.size() == 2, "convex hull triangleContains to many points");
	}
}
