abstract class SmartDevice {
    protected int id;
    protected String type;
    protected boolean isOn;

    public SmartDevice(int id, String type) {
        this.id = id;
        this.type = type;
        this.isOn = false;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(type + " " + id + " is now ON.");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println(type + " " + id + " is now OFF.");
    }

    public abstract String getStatus();
}

class Light extends SmartDevice {
    public Light(int id) {
        super(id, "Light");
    }

    @Override
    public String getStatus() {
        return "Light " + id + " is " + (isOn ? "On" : "Off");
    }
}

class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(int id, int temperature) {
        super(id, "Thermostat");
        this.temperature = temperature;
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Thermostat " + id + " is set to " + temp + " degrees.");
    }

    @Override
    public String getStatus() {
        return "Thermostat " + id + " is set to " + temperature + " degrees";
    }

    public int getTemperature() {
        return temperature;
    }
}

class DoorLock extends SmartDevice {
    public DoorLock(int id) {
        super(id, "Door");
    }

    @Override
    public String getStatus() {
        return "Door " + id + " is " + (isOn ? "Locked" : "Unlocked");
    }

    public void lock() {
        this.isOn = true;
        System.out.println("Door " + id + " is now LOCKED.");
    }

    public void unlock() {
        this.isOn = false;
        System.out.println("Door " + id + " is now UNLOCKED.");
    }
}
class DeviceFactory {
    public static SmartDevice createDevice(String type, int id, int temperature) {
        switch (type.toLowerCase()) {
            case "light":
                return new Light(id);
            case "thermostat":
                return new Thermostat(id, temperature);
            case "door":
                return new DoorLock(id);
            default:
                throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}
