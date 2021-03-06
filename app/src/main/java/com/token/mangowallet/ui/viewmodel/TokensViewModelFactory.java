package com.token.mangowallet.ui.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.token.mangowallet.MyApplication;
import com.token.mangowallet.interact.FetchTokensInteract;
import com.token.mangowallet.interact.FetchWalletInteract;
import com.token.mangowallet.repository.EMWalletRepository;
import com.token.mangowallet.repository.EthereumNetworkRepository;
import com.token.mangowallet.repository.RepositoryFactory;
import com.token.mangowallet.repository.TokenRepository;

import one.block.eosiojavarpcprovider.implementations.EosioJavaRpcProviderImpl;

public class TokensViewModelFactory implements ViewModelProvider.Factory {

    private final TokenRepository tokenRepository;
    private final EthereumNetworkRepository ethereumNetworkRepository;

    public TokensViewModelFactory() {
        RepositoryFactory rf = MyApplication.repositoryFactory();
        ethereumNetworkRepository = rf.ethereumNetworkRepository;
        tokenRepository = rf.tokenRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TokensViewModel(
                ethereumNetworkRepository,
                new FetchWalletInteract(),
                new EMWalletRepository(),
                tokenRepository);
    }
}
