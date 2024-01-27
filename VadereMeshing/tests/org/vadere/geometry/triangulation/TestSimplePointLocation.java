package org.vadere.geometry.triangulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vadere.meshing.mesh.gen.PFace;
import org.vadere.meshing.mesh.gen.PHalfEdge;
import org.vadere.meshing.mesh.gen.PMesh;
import org.vadere.meshing.mesh.gen.PVertex;
import org.vadere.meshing.mesh.inter.IMesh;
import org.vadere.meshing.mesh.inter.IIncrementalTriangulation;

import static  org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Benedikt Zoennchen
 */
public class TestSimplePointLocation {

	private IMesh<PVertex, PHalfEdge, PFace> mesh;
	private IIncrementalTriangulation<PVertex, PHalfEdge, PFace> triangulation;
	private long numberOfPoints = 100;

	@BeforeEach
	public void setUp() throws Exception {
		mesh = new PMesh();
		triangulation = IIncrementalTriangulation.generateRandomTriangulation(numberOfPoints);
	}

	@Test
	public void testLocateAllVertices() {
		assertTrue(triangulation.getMesh().getVertices().size() > numberOfPoints * 0.1);
		triangulation.getMesh().getVertices().forEach(p -> assertTrue(triangulation.locate(p.getX(), p.getY()).isPresent()));
	}



}
