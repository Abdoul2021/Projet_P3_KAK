package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    //Four methods added : getNeighbourByPosition, getNeighboursFavorites, addNeighbourFavorites, deleteNeighbourFavorites

    // Get a neighbour by position with success

    @Test
    public void getNeighbourByPositionWithSuccess(){
        int position = 1;
        Neighbour expectedNeighbour = service.getNeighbourByPosition(position);
        Neighbour neighbour = service.getNeighbours().get(position);
        assertEquals(expectedNeighbour.getName(), neighbour.getName());
    }

    //Get a favorite with success
    @Test
    public void getNeighboursFavoritesWithSuccess() {
        List<Neighbour> expectedFavoritesNeighbours = service.getNeighboursFavorites();
        assertTrue(expectedFavoritesNeighbours.isEmpty());
        }

    //Add a favorite with success
    @Test
    public void addFavoriteNeighboursWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addNeighbourFavorites(neighbour);
        assertFalse(service.getNeighboursFavorites().isEmpty());
    }

    //Delete a favorite with success
    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addNeighbourFavorites(neighbour);
        assertFalse(service.getNeighboursFavorites().isEmpty());
        service.deleteNeighbourFavorites(neighbour);
        assertTrue(service.getNeighboursFavorites().isEmpty());
    }
}
