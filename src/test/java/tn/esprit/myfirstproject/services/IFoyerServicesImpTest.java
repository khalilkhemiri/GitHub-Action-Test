package tn.esprit.myfirstproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.myfirstproject.entities.Bloc;
import tn.esprit.myfirstproject.entities.Foyer;
import tn.esprit.myfirstproject.entities.Universite;
import tn.esprit.myfirstproject.repositories.IBlocRepository;
import tn.esprit.myfirstproject.repositories.IEtudiantRepository;
import tn.esprit.myfirstproject.repositories.IFoyerRepository;
import tn.esprit.myfirstproject.repositories.IUniversiteRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IFoyerServicesImpTest {

    @Mock
    private IFoyerRepository foyerRepository;

    @Mock
    private IUniversiteRepository universiteRepository;

    @Mock
    private IBlocRepository blocRepository;

    @Mock
    private IEtudiantRepository etudiantRepository;

    @InjectMocks
    private IFoyerServicesImp foyerServices;

    private Foyer foyer;
    private Universite universite;
    private Bloc bloc;

    @BeforeEach
    public void setUp() {
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Foyer A");
        foyer.setCapaciteFoyer(50L);

        // Initialisez l'attribut blocs avec un HashSet vide
        foyer.setBlocs(new HashSet<>());

        universite = new Universite();
        universite.setIdUniversite(1L);
        universite.setNomUniversite("Université XYZ");

        bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc 1");

        foyer.setUniversite(universite);
        foyer.getBlocs().add(bloc); // Maintenant, foyer.getBlocs() ne sera pas null
    }
    @Test
    public void testAjouterFoyer() {
        // Arrange
        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        // Act
        Foyer savedFoyer = foyerServices.ajouterFoyer(foyer);

        // Assert
        assertEquals(foyer, savedFoyer);
    }

    @Test
    public void testAjouterFoyerEtAffecterAUniversite() {
        // Arrange
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);

        // Act
        Foyer updatedFoyer = foyerServices.ajouterFoyerEtAffecterAUniversite(foyer, 1L);

        // Assert
        assertEquals(universite, updatedFoyer.getUniversite());
        assertEquals(1, updatedFoyer.getBlocs().size());
    }

    @Test
    public void testUpdateFoyer() {
        // Arrange
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));
        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        // Act
        Foyer updatedFoyer = foyerServices.updateFoyer(foyer);

        // Assert
        assertEquals("Foyer A", updatedFoyer.getNomFoyer());
    }

    @Test
    public void testGetAllFoyers() {
        // Arrange
        List<Foyer> foyers = new ArrayList<>();
        foyers.add(foyer);
        when(foyerRepository.findAll()).thenReturn(foyers);

        // Act
        List<Foyer> result = foyerServices.getAllFoyers();

        // Assert
        assertEquals(1, result.size());
        assertEquals(foyer, result.get(0));
    }

    @Test
    public void testGetFoyersWithoutUniversite() {
        // Arrange
        List<Foyer> foyersWithoutUniversite = new ArrayList<>();
        foyersWithoutUniversite.add(foyer);
        when(foyerRepository.findByUniversiteIsNull()).thenReturn(foyersWithoutUniversite);

        // Act
        List<Foyer> result = foyerServices.getFoyersWithoutUniversite();

        // Assert
        assertEquals(1, result.size());
        assertEquals(foyer, result.get(0));
    }

    @Test
    public void testGetFoyerById() {
        // Arrange
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        // Act
        Foyer result = foyerServices.getFoyerById(1L);

        // Assert
        assertEquals(foyer, result);
    }





}
