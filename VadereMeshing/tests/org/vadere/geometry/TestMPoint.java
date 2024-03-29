package org.vadere.geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vadere.meshing.mesh.triangulation.improver.eikmesh.EikMeshPoint;

import java.util.HashSet;

import static  org.junit.jupiter.api.Assertions.assertFalse;
import static  org.junit.jupiter.api.Assertions.assertTrue;

public class TestMPoint {

	@BeforeEach
	public void setUp() {

	}

	@Test
	public void testHashSet() {
		EikMeshPoint mPoint1 = new EikMeshPoint(3.0, 1.43545, false);
		EikMeshPoint mPoint2 = new EikMeshPoint(3.0, 1.43545, false);
		EikMeshPoint mPoint3 = new EikMeshPoint(3.0, 1.43545, true);
		HashSet<EikMeshPoint> set = new HashSet<>();
		assertTrue(set.add(mPoint1));
		assertFalse(set.add(mPoint2));
		assertFalse(set.add(mPoint3));
		assertTrue(set.size() == 1);
	}
}
