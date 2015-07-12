import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class NeuralCellTest
{
		@Test
		public void DendrytesAndSynapsesCanBeAdded()
		{
				NeuralCell ncell = new NeuralCell();
				ncell.addInput();
				ncell.addInput();
				for(int i = 1; i<=3; i++)
					ncell.addInput();
				assertEquals(5, ncell.getInputSize());
		}
		

		@Test
		public void InputDataCanBeSetAndChecked()
		{
				NeuralCell ncell = new NeuralCell();
				ncell.addInput();
				ncell.setInputData(0, 3);
				assertEquals(3.0, ncell.getInputData(0), 0.001);
		}
		
		@Test
		public void InputWeightCanBeSetAndChecked()
		{
			NeuralCell ncell = new NeuralCell();
			ncell.addInput();
			ncell.setInputWeight(0, 2);
			assertEquals(2, ncell.getInputWeight(0), 0.001);
		}
		
		@Test
		public void ManyNodesCanBeAddedAtOnce()
		{
			NeuralCell ncell = new NeuralCell();
			ncell.addInput(2500);
			assertEquals(2500, ncell.getInputSize());
		}
		
		@Test
		public void OneInputCanBeProcessed()
		{
			NeuralCell ncell = new NeuralCell();
			ncell.addInput();
			ncell.setInputData(0, 22);
			ncell.setInputWeight(0, 0.17);
			assertEquals(3.74, ncell.processCellNode(0), 0.001);
		}
		
		@Test
		public void ManyInputsCanBeProcessed()
		{
			NeuralCell ncell = new NeuralCell();
			ncell.addInput(200);
			for(int i = 0; i<200; i++)
			{
				Random rand = new Random();
				
				double data = rand.nextDouble()*100;
				double weight = rand.nextDouble();
				
				ncell.setInputData(i, data);
				ncell.setInputWeight(i, weight);
				System.out.println("INPUT{ data="+data+",\tweight="+weight+"}\tRESULT{result="+ncell.processCellNode(i)+"}");
				assertEquals(data*weight, ncell.processCellNode(i), 0.001);
			}
		}
		
		@Test
		public void MembranePotentialCanBeCalculated()
		{
			NeuralCell ncell = new NeuralCell();
			ncell.addInput(500);
			double sum = 0;
			for(int i = 0; i<500; i++)
			{
				Random rand = new Random();
				
				double data = rand.nextDouble()*100;
				double weight = rand.nextDouble();
				
				ncell.setInputData(i, data);
				ncell.setInputWeight(i, weight);
				sum += data*weight;
			}
			System.out.println("Example membrane potential: "+ncell.getMembranePotential());
			assertEquals(sum, ncell.getMembranePotential(), 0.001);
		}
	
		@Test
		public void MembranePotentialCannotBeCalculatedIfInputIsNotSet()
		{
			NeuralCell ncell = new NeuralCell();
			assertEquals(-1, ncell.getMembranePotential(), 0.001);
		}
		
		@Test
		public void CanGetProfiledOutput()
		{
			NeuralCell ncell = new NeuralCell()
			{
				@Override
				public double finalizeData(double membranePotential)
				{
						return super.finalizeData(membranePotential);
				}
			};
			ncell.addInput(2);
			ncell.setInputData(0, 2);
			ncell.setInputWeight(0, 0.5);
			ncell.setInputData(1, 2);
			ncell.setInputWeight(1, 2);
			assertEquals(5.0, ncell.getOutput(), 1);
		}
		
		@Test
		public void CellOutputFailsWhenNoData()
		{
			NeuralCell ncell = new NeuralCell();
			assertEquals(-1, ncell.getOutput(), 1);
		}
		
}
