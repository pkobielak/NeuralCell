import java.util.ArrayList;
import java.util.List;

public class NeuralCell
{
		/**
		 * NeuralCell Body
		 */
		private List<Double> Dendrites;
		private List<Double> Synapses;
		
		/**
		 * Constructor.
		 */
		public NeuralCell()
		{
			Dendrites = new ArrayList<>(100);
			Synapses = new ArrayList<>(100);
		}
		
		/**
		 * Method to override to get unique output calculation.
		 * @param membranePotential
		 * @return	Output of neural cell calculations.
		 */
		public double finalizeData(double membranePotential)
		{
			return membranePotential;
		}
		
		/**
		 * 
		 * @return Quantity of existing dendrites and synapses.
		 */
		public int getInputSize()
		{
			return Dendrites.size();
		}
		
		/**
		 * Adds new field for input.
		 */
		public void addInput()
		{
			Dendrites.add(0.0);
			Synapses.add(1.0);
		}
		
		/**
		 * Adds selected quantity of input fields.
		 * @param count
		 */
		public void addInput(int count)
		{
			for(int i = 1; i <= count; i++)
				this.addInput();
		}
		
		/**
		 * Used to check selected dendrite contained value.
		 * @param index
		 * @return Contained value of selected dendrite.
		 */
		public double getInputData(int index)
		{
			return Dendrites.get(index);
		}
		
		/**
		 * Sets value of dendrite at selected index.
		 * @param index
		 * @param value
		 */
		public void setInputData(int index, double value)
		{
			Dendrites.set(index, value);
		}
		
		/**
		 * Used to check selected synapse contained weight.
		 * @param index
		 * @return Contained value of selected synapse.
		 */
		public double getInputWeight(int index)
		{
			return Synapses.get(index);
		}
		
		/**
		 * Sets value of synapse at selected index.
		 * @param index
		 * @param value
		 */
		public void setInputWeight(int index, double weight)
		{
			Synapses.set(index, weight);
		}
		
		/**
		 * Processes contained data in a single Cell Node which is a dendrite
		 * and synapse assigned to it.
		 * @param index
		 * @return Result of processing node data.
		 */
		public double processCellNode(int index)
		{
			return (Dendrites.get(index)*Synapses.get(index));
		}
		
		/**
		 * Calculates membrane potential of single neural cell.
		 * @return Membrane potential of neural cell or -1 when input is not set.
		 */
		public double getMembranePotential()
		{
			if(getInputSize() == 0)
				return -1;
			
			double sum = 0;
			for (int i = 0; i < getInputSize(); i++)
				sum+=processCellNode(i);

			return sum;
		}
		
		/**
		 * Calculates output of single neuron cell.
		 * @return Calculated neuron cell output or -1 when input is not set.
		 */
		public double getOutput()
		{
			if(getInputSize() == 0)
				return -1;
				
			return finalizeData(getMembranePotential());
		}
}
