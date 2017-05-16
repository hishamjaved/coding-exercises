package com.design.patterns;

/**
 * Creational Design Pattern
 */
public class AbstractFactory {
    public static void main(String[] args){
        GUIBuilder builder = new GUIBuilder();
        AbstractWidgetFactory widgetFactory = null;
        String platform = "MACOSX";
        //check what platform we're on
        if(platform.equals("MACOSX")){
            widgetFactory  = new MacOSXWidgetFactory();
        } else {
            widgetFactory  = new MsWindowsWidgetFactory();
        }
        builder.buildWindow(widgetFactory);
    }


}

//Our AbstractProduct
interface Window{
    public void setTitle(String text);
    public void repaint();
}


//ConcreteProductA1
class MSWindow implements Window{
    public void setTitle(String text){
        //MS Windows specific behaviour
        System.out.println("Title: "+ text +" of Windows OS");
    }
    public void repaint(){
        //MS Windows specific behaviour
    }
}

//ConcreteProductA2
class MacOSXWindow implements Window{
    public void setTitle(String text){
        //Mac OSX specific behaviour
        System.out.println("Title: "+ text+" of Mac OSX");
    }
    public void repaint(){
        //Mac OSX specific behaviour
    }
}


//com.design.patterns.AbstractFactory
interface AbstractWidgetFactory{
    public Window createWindow();
}

//ConcreteFactory1
class MsWindowsWidgetFactory implements AbstractWidgetFactory{
    //create an com.design.patterns.MSWindow
    public Window createWindow(){
        MSWindow window = new MSWindow();
        return window;
    }
}

//ConcreteFactory2
class MacOSXWidgetFactory implements AbstractWidgetFactory{
    //create a com.design.patterns.MacOSXWindow
    public Window createWindow(){
        MacOSXWindow window = new MacOSXWindow();
        return window;
    }
}

//Client
class GUIBuilder{
    public void buildWindow(AbstractWidgetFactory widgetFactory){
        Window window = widgetFactory.createWindow();
        window.setTitle("New com.design.patterns.Window");

    }
}