using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using MarketDataAnalyser;

namespace AnalyserUnitTest
{
    [TestClass]
    public class UnitTest
    {
        [TestMethod]
        public void PipelineTest()
        {
            
            PipelineTest newPipelineTest = new PipelineTest("ABCD");

           // Assert.AreEqual(newPipelineTest.TestTheWiring(), "ABCA");
            Assert.AreEqual(newPipelineTest.TestTheStockName(), "ABCD"); 
           

        }
    }
}
