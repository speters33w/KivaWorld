import solver.CreateMap;
import solver.SolveMap;

public class KivaRunner {
    public static void main(String[] args) {
        CreateMap kivaCreateMap = new solver.CreateMap();
        String map = kivaCreateMap.randomMapString();
        System.out.println(map);
        String floorMapFileName = kivaCreateMap.saveFile(map);
        SolveMap.setKivaCommands(true);
        String commands = SolveMap.solve(SolveMap.getFile(floorMapFileName));
        FloorMap floorMap = new FloorMap(map);
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.run(floorMap, commands);
    }
}
