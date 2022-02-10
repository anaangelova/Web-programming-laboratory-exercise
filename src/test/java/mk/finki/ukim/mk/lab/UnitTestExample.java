package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonJpaRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerJpaRepository;
import mk.finki.ukim.mk.lab.service.interfaces.BalloonService;
import mk.finki.ukim.mk.lab.service.impl.BalloonServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UnitTestExample {

    @Mock
    private BalloonJpaRepository balloonRepository;

    @Mock
    private ManufacturerJpaRepository manufacturerRepository;

    private BalloonService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Manufacturer manufacturer=new Manufacturer("name","contry","address");
        Balloon balloon=new Balloon("name","desc",manufacturer);
       /* Mockito.when(this.balloonRepository.save(Mockito.any(Balloon.class))).thenReturn(balloon);
        Mockito.when(this.manufacturerRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(manufacturer));
*/

        this.service = Mockito.spy(new BalloonServiceImpl(this.balloonRepository, this.manufacturerRepository));
    }

    @Test
    public void testSaveBallon(){

        Assert.assertThrows("Null manf", ManufacturerNotFoundException.class,
                () -> this.service.save("balon","desc", null));
        Mockito.verify(this.service).save("balon","desc",null);
    }



}
