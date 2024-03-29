package org.vadere.simulator.projects.dataprocessing.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vadere.simulator.control.simulation.SimulationState;
import org.vadere.state.attributes.processor.AttributesPedestrianWaitingEndTimeProcessor;

import static  org.junit.jupiter.api.Assertions.*;

public class PedestrianWaitingEndTimeProcessorTest extends ProcessorTest {

	@BeforeEach
	public void setup(){
		processorTestEnv = new PedestrianWaitingEndTimeProcessorTestEnv();
		super.setup();
	}

	@Test
	public void doUpdate() throws Exception {
	}

	@Test
	public void init() throws Exception {
		assertInit(p);
		AttributesPedestrianWaitingEndTimeProcessor attr =
				(AttributesPedestrianWaitingEndTimeProcessor) p.getAttributes();

		for (SimulationState s : processorTestEnv.getSimStates()) {
			p.update(s);
		}
		processorTestEnv.getOutputFile().write();

		assertEquals(processorTestEnv.getOutput().size(), p.getData().size());
		assertEquals(processorTestEnv.getSimStates().size(), (int) r.valOfField("lastStep"));

		p.init(processorTestEnv.getManager());
		assertInit(p);
	}

	@Test
	public void getAttributes() throws Exception {
	}

}