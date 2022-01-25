package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.utils.BaseConocimiento;
import ch.idsia.utils.TrainFileManager;

import java.util.Random;

public class IBLBotAgent extends BasicMarioAIAgent implements Agent {
    private Random R = null;
    private Environment environment;
    private TrainFileManager trainFileManager;
    private BaseConocimiento baseConocimiento;
    private boolean[] action = null;
    private int ticks;

    public IBLBotAgent() {
        super("IBLBotAgent");
        reset();
        trainFileManager = new TrainFileManager("IBLBotAgent.arff");
        baseConocimiento = new BaseConocimiento();
        ticks = 0;
    }

    public void reset() {
        // Dummy reset, of course, but meet formalities!
        R = new Random();
    }

    /**
     * Se utiliza el parámetro para asignar valor al atributo de la clase con el mismo nombre.
     *
     * @param environment Objeto de tipo Environment con toda la información sobre el estado del mundo.
     */
    public void integrateObservation(Environment environment) {
        this.environment = environment;
    }

    /**
     * Se analizan los alrededores del personaje, de forma que avance a la derecha constantemente y salte cuando
     * tenga enemigos, obstáculos y pozos delante.
     *
     * @return action
     */
    public boolean[] getAction() {
        String[] linea;
        linea = trainFileManager.getDatos(environment).split(", ");
        //System.out.printf("%d; %d; %d; %d\n", ticks, environment.getEvaluationInfoAsInts()[1], environment.getIntermediateReward(), environment.getMarioMode());
        ticks++;
        return baseConocimiento.getNextAction(linea);
    }
}