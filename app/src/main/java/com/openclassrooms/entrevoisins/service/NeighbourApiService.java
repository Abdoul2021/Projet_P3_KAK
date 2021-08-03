package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Delete a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get by position
     * @param position
     */
    Neighbour getNeighbourByPosition(int position);

    Neighbour getNeighbourFavoriteByPosition(int position);

    /**
     * Get a neighbour favorite
     * @param
     */
    List<Neighbour> getNeighboursFavorites();

    /**
     * Add a neighbour in the favorites list
     * @param neighbour
     */
    void addNeighbourFavorites(Neighbour neighbour);

    /**
     * Delete a neighbour to the favorites list
     * @param neighbour
     */
    void deleteNeighbourFavorites(Neighbour neighbour);
}
