package es.usal;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteClasificador extends Agent{
	private static final long serialVersionUID = 1L;
	private CyclicBehaviourClasificador cbc = new CyclicBehaviourClasificador(this);

	public void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName("Clasificador");
		sd.setType("ClasificarRelacion");
		sd.addOntologies("ontologia");
		sd.addLanguages(new SLCodec().getName());
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd);
		}
		catch(FIPAException e){
			System.err.println("Agente" + getLocalName() + ": " + e.getMessage());
		}
		
		cbc = new CyclicBehaviourClasificador(this);
		this.addBehaviour(cbc);
	}
}

