package org.vadere.simulator.control.psychology.perception.models;

import org.vadere.simulator.models.Model;
import org.vadere.state.attributes.models.psychology.perception.AttributesPerceptionModel;
import org.vadere.state.psychology.perception.types.ElapsedTime;
import org.vadere.state.psychology.perception.types.Stimulus;
import org.vadere.state.scenario.Pedestrian;
import org.vadere.state.scenario.Topography;

import java.util.HashMap;
import java.util.List;

/*
 * Interface for a perception model.
 *
 * A perception model models the perception of an agent.
 * An agent perceives stimuli from the environment like for example a sound or visual information (see {@link Stimulus} for stimuli available).
 * Basically, the perception model behaves like a filter for stimuli.
 * Only stimuli whose intensity exceed a certain threshold are forwarded to the {@link org.vadere.simulator.control.psychology.cognition.models.ICognitionModel} .
 * Please find the Sensory threshold theory for background on the theory.
 * Note that any function could be implemented to model the sensory process.
 * In {@link SimplePerceptionModel} and {@link MultiPerceptionModel}, we model the sensory process in a very simplistic way.
 * Please see the respective model descriptions.
 * {@link Pedestrian} at a specific simulation step based on pedestrian's
 * attributes or its surrounding. It is designed as an interface so that
 * different models can be used for different scenarios (by specifying in the
 * JSON file).
 *
 * The <code>initialize</code> method must be called before usage!
 * This interface defines callbacks for the simulation loop.
 * It's implementations define the major part of the simulation model's logic.
 *
 * This approach is similar to the {@link Model} interface for locomotion models.
 */
public interface IPerceptionModel {

	/**
	 * This method initializes the model. It gets the {@link Topography} so that
	 * a model can acquire additional information about a pedestrian's surrounding
	 * when evaluating pedestrian's perception.
	 */
	void initialize(Topography topography, final double simTimeStepLengh);


	/**
	 * Usually, this method iterates over the pededestrians and calls
	 * {@link Pedestrian#setMostImportantStimulus(Stimulus)}.
	 *
	 * The current simulation time (in seconds) can be extracted from the list of
	 * stimuli. It is expected that the list contains an {@link ElapsedTime}.
	 *  */
	void update(HashMap<Pedestrian, List<Stimulus>> pedSpecificStimuli);

	void setAttributes(AttributesPerceptionModel attributes);

	AttributesPerceptionModel getAttributes();


}
