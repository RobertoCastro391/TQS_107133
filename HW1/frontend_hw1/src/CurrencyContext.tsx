import React, { createContext, useContext, useState, useCallback, ReactNode } from 'react';

interface Currency {
  code: string;
  name: string;
  symbol: string;
  exchangeRate: {
    currencyCode: number;
  };
}

interface CurrencyContextType {
  currency: Currency;
  updateCurrency: (newCurrency: Currency) => void;
}

// Default values for the context
const defaultState = {
  currency: {
    code: 'EUR',
    name: 'Euro',
    symbol: '€',
    exchangeRate: {
      currencyCode: 1,
    },
  },
  updateCurrency: () => {},
};

const CurrencyContext = createContext<CurrencyContextType>(defaultState);

// Custom hook for accessing context
export const useCurrency = () => useContext(CurrencyContext);

interface Props {
  children: ReactNode;
}

export const CurrencyProvider: React.FC<Props> = ({ children }) => {
  const [currency, setCurrency] = useState<Currency>(defaultState.currency);

  const updateCurrency = useCallback((newCurrency: Currency) => {
    setCurrency(newCurrency);
  }, []);

  return (
    <CurrencyContext.Provider value={{ currency, updateCurrency }}>
      {children}
    </CurrencyContext.Provider>
  );
};
