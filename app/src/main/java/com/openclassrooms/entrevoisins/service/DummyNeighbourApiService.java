package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> neighbourFavorites = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getNeighbourByPosition(int position) {
        return neighbours.get(position);
    }

    @Override
    public List<Neighbour> getNeighboursFavorites() {
        return neighbourFavorites;
    }

    @Override
    public void addNeighbourFavorites(Neighbour neighbour) {neighbourFavorites.add(neighbour);
    }

    @Override
    public void deleteNeighbourFavorites(Neighbour neighbour) {neighbourFavorites.remove(neighbour);

    }
}
