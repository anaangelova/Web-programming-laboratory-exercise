package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    private final List<Balloon> balloonList;

    public BalloonRepository(){
        this.balloonList=new ArrayList<>();
        balloonList.add(new Balloon("balloon1","desc for ballon 1"));
        balloonList.add(new Balloon("balloon2","desc for ballon 2"));
        balloonList.add(new Balloon("balloon3","desc for ballon 3"));
        balloonList.add(new Balloon("balloon4","desc for ballon 4"));
        balloonList.add(new Balloon("balloon5","desc for ballon 5"));
        balloonList.add(new Balloon("balloon6","desc for ballon 6"));
        balloonList.add(new Balloon("balloon7","desc for ballon 7"));
        balloonList.add(new Balloon("balloon8","desc for ballon 8"));
        balloonList.add(new Balloon("balloon9","desc for ballon 9"));
        balloonList.add(new Balloon("balloon10","desc for ballon 10"));

    }
    public List<Balloon> findAllBalloons(){
        return balloonList;
    }
    public List<Balloon> findAllByNameOrDescription(String text){
        return balloonList.stream().filter(balloon -> balloon.getName().equals(text) || balloon.getDescription().equals(text)).collect(Collectors.toList());
    }
}
