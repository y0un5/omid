package com.yahoo.omid.tso;

import java.io.IOException;

/**
 * Allows to reset the TSO state and register observers for being notified
 * when changes occur
 */
public interface TSOStateManager {

    /**
     * Represents the state of the TSO
     */
    public static class TSOState {

        // TSO state variables
        private final long lowWatermark;

        public TSOState(long lowWatermark, long epoch) {
            this.lowWatermark = lowWatermark;
        }

        public long getLowWatermark() {
            return lowWatermark;
        }

        public long getEpoch() {
            // In this implementation the epoch == low watermark
            return lowWatermark;
        }

        @Override
        public String toString() {
            return String.format("LWM %d/Epoch %d", getLowWatermark(), getEpoch());
        }

    }

    /**
     * Allows implementors to receive the new state when changes occur
     */
    public interface StateObserver {

        /**
         * Notifies the observer about the change in state
         * @param state
         *            the new TSOState
         */
        public void update(TSOState state) throws IOException;

    }

    /**
     * Allows to register observers for receiving state changes
     *
     * @param observer
     *            the observer to register
     */
    public void register(StateObserver observer);

    /**
     * Allows to de-register observers for stopping receiving changes
     *
     * @param observer
     *            the observer to unregister
     */
    public void unregister(StateObserver observer);

    /**
     * Allows to reset the state
     *
     * @return the new state after reset
     * @throws IOException
     *             when problems resetting occur
     */
    public TSOState reset() throws IOException;

}