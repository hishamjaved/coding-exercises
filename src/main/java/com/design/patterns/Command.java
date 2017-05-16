package com.design.patterns;

/**
 * Behavioral Patterns
 */
public class Command {
    public static void main(String[] args)    {
        RemoteControl control = new RemoteControl();
        Light light = new Light();
        ICommand lightsOn = new LightOnCommand(light);
        ICommand lightsOff = new LightOffCommand(light);
        //switch on
        control.setCommand(lightsOn);
        control.pressButton();
        //switch off
        control.setCommand(lightsOff);
        control.pressButton();
    }
}

//com.design.patterns.Command
interface ICommand{
    public void execute();
}

//Concrete com.design.patterns.Command
class LightOnCommand implements ICommand{
    //reference to the light
    Light light;
    public LightOnCommand(Light light){
        this.light = light;
    }
    public void execute(){
        light.switchOn();
    }
}

//Concrete com.design.patterns.Command
class LightOffCommand implements ICommand{
    //reference to the light
    Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }
    public void execute(){
        light.switchOff();
    }
}


//Receiver
class Light{
    private boolean on;
    public void switchOn(){
        on = true;
        System.out.println("ON");
    }
    public void switchOff(){
        on = false;
        System.out.println("OFF");
    }
}


//Invoker
class RemoteControl{
    private ICommand command;
    public void setCommand(ICommand command){
        this.command = command;
    }
    public void pressButton(){
        command.execute();
    }
}
