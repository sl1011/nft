package com.shitouren.core.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes4;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Charge extends Contract {
    public static final String BINARY = "60a06040523060805234801561001457600080fd5b50600054610100900460ff168061002e575060005460ff16155b6100955760405162461bcd60e51b815260206004820152602e60248201527f496e697469616c697a61626c653a20636f6e747261637420697320616c72656160448201526d191e481a5b9a5d1a585b1a5e995960921b606482015260840160405180910390fd5b600054610100900460ff161580156100b7576000805461ffff19166101011790555b80156100c9576000805461ff00191690555b506080516120ca6100fa600039600081816104c3015281816105030152818161058c01526105cc01526120ca6000f3fe6080604052600436106100fd5760003560e01c8063715018a611610095578063c5837d8211610064578063c5837d821461026d578063c9c45a0f1461028d578063d213fe45146102c2578063ef18e3c9146102e2578063f2fde38b1461030257600080fd5b8063715018a6146101fb5780638129fc1c146102105780638a27a80d146102255780638da5cb5b1461024557600080fd5b80633659cfe6116100d15780633659cfe6146101885780634f1ef286146101a857806363569189146101bb57806370a08231146101db57600080fd5b80620b8f7014610102578063093f28e01461012457806318160ddd1461014457806336351c7c14610168575b600080fd5b34801561010e57600080fd5b5061012261011d366004611a09565b610322565b005b34801561013057600080fd5b5061012261013f366004611a3c565b6103a5565b34801561015057600080fd5b5060c9545b6040519081526020015b60405180910390f35b34801561017457600080fd5b50610122610183366004611a6f565b610441565b34801561019457600080fd5b506101226101a3366004611a09565b6104b8565b6101226101b6366004611b08565b610581565b3480156101c757600080fd5b506101226101d6366004611b99565b61063b565b3480156101e757600080fd5b506101556101f6366004611a09565b6106fb565b34801561020757600080fd5b5061012261073f565b34801561021c57600080fd5b50610122610775565b34801561023157600080fd5b50610122610240366004611a6f565b6107f0565b34801561025157600080fd5b506033546040516001600160a01b03909116815260200161015f565b34801561027957600080fd5b50610122610288366004611a09565b6108de565b34801561029957600080fd5b506102ad6102a8366004611a3c565b610980565b60405163ffffffff909116815260200161015f565b3480156102ce57600080fd5b506101226102dd366004611be9565b610a5d565b3480156102ee57600080fd5b506101226102fd366004611c02565b610ac8565b34801561030e57600080fd5b5061012261031d366004611a09565b610bc5565b6001600160a01b0381166103515760405162461bcd60e51b815260040161034890611c3e565b60405180910390fd5b610359610c5d565b6001600160a01b038116600081815260ca6020526040808220600101805460ff19169055517f0ba05d508af447342f624920545278b6e2d2320ee40cb9eff56b89d21e1b25e89190a250565b6001600160a01b0382166103cb5760405162461bcd60e51b815260040161034890611c3e565b6103d3610c5d565b6001600160a01b038216600081815260ca602090815260408083206001600160e01b0319861680855290835292819020805463ffffffff19169055519182527f2f93e067617701594eddb2443d90441c5bb959df555ae8e4d150f0a8bf8b006d910160405180910390a25050565b8061045e5760405162461bcd60e51b815260040161034890611c6b565b6104683383610d2a565b610473338383610e44565b6040518181526001600160a01b0383169033907f4ade20d82044693c0d3331ba1d2a482d103734f274166989491c8d30d3f2ecb1906020015b60405180910390a35050565b306001600160a01b037f00000000000000000000000000000000000000000000000000000000000000001614156105015760405162461bcd60e51b815260040161034890611ca0565b7f00000000000000000000000000000000000000000000000000000000000000006001600160a01b0316610533610f36565b6001600160a01b0316146105595760405162461bcd60e51b815260040161034890611cec565b61056281610f64565b6040805160008082526020820190925261057e91839190610f8e565b50565b306001600160a01b037f00000000000000000000000000000000000000000000000000000000000000001614156105ca5760405162461bcd60e51b815260040161034890611ca0565b7f00000000000000000000000000000000000000000000000000000000000000006001600160a01b03166105fc610f36565b6001600160a01b0316146106225760405162461bcd60e51b815260040161034890611cec565b61062b82610f64565b61063782826001610f8e565b5050565b6001600160a01b0383166106615760405162461bcd60e51b815260040161034890611c3e565b610669610c5d565b6001600160a01b038316600081815260ca602090815260408083206001808201805460ff191690911790556001600160e01b0319871680855290835292819020805463ffffffff191663ffffffff87169081179091558151938452918301919091527f929dc21675623ba3d42ef9e085962b7d88bf57ba159fe8f0a86d6785195e2acc910160405180910390a2505050565b60006001600160a01b0382166107235760405162461bcd60e51b815260040161034890611c3e565b506001600160a01b0316600090815260cb602052604090205490565b6033546001600160a01b031633146107695760405162461bcd60e51b815260040161034890611d38565b61077360006110d9565b565b600054610100900460ff168061078e575060005460ff16155b6107aa5760405162461bcd60e51b815260040161034890611d6d565b600054610100900460ff161580156107cc576000805461ffff19166101011790555b6107d461112b565b6107dc611192565b801561057e576000805461ff001916905550565b8061080d5760405162461bcd60e51b815260040161034890611c6b565b6001600160a01b0382163b1515801561084157506001600160a01b038216600090815260ca602052604090206001015460ff165b61088d5760405162461bcd60e51b815260206004820152601860248201527f6368617267653a206e6f742044444320636f6e747261637400000000000000006044820152606401610348565b610895610c5d565b6108a182335b83610e44565b6040518181526001600160a01b0383169033907fca2ce982d63c71a419517d389df253be4b0d6f4da85fe1491e49608b139ee0be906020016104ac565b6033546001600160a01b031633146109085760405162461bcd60e51b815260040161034890611d38565b6001600160a01b03811661095e5760405162461bcd60e51b815260206004820152601d60248201527f6368617267653a206175746820746865207a65726f20616464726573730000006044820152606401610348565b60cc80546001600160a01b0319166001600160a01b0392909216919091179055565b60006001600160a01b0383166109a85760405162461bcd60e51b815260040161034890611c3e565b6001600160a01b038316600090815260ca602052604090206001015460ff16610a215760405162461bcd60e51b815260206004820152602560248201527f6368617267653a6464632070726f787920636f6e747261637420756e617661696044820152646c61626c6560d81b6064820152608401610348565b506001600160a01b038216600090815260ca602090815260408083206001600160e01b03198516845290915290205463ffffffff165b92915050565b80610a7a5760405162461bcd60e51b815260040161034890611c6b565b610a82610c5d565b610a8d60003361089b565b60405181815233906000907f4ade20d82044693c0d3331ba1d2a482d103734f274166989491c8d30d3f2ecb19060200160405180910390a350565b6001600160a01b038316610aee5760405162461bcd60e51b815260040161034890611c3e565b80610b325760405162461bcd60e51b815260206004820152601460248201527318da185c99d94e9a5b9d985b1a5908191918d25960621b6044820152606401610348565b336000610b3f8285610980565b905063ffffffff811615610b5e57610b5e85838363ffffffff16610e44565b604080516001600160e01b03198616815263ffffffff831660208201529081018490526001600160a01b0380841691908716907f750e56f33a72767cd99db8943f4d04ca416c55fb783003107a869f5d5062dbab9060600160405180910390a35050505050565b6033546001600160a01b03163314610bef5760405162461bcd60e51b815260040161034890611d38565b6001600160a01b038116610c545760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b6064820152608401610348565b61057e816110d9565b60cc546001600160a01b031663ed5cad643360006040518363ffffffff1660e01b8152600401610c8e929190611dd1565b602060405180830381865afa158015610cab573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610ccf9190611e0c565b6107735760405162461bcd60e51b815260206004820152602660248201527f4444433732313a6e6f742061206f70657261746f7220726f6c65206f722064696044820152651cd8589b195960d21b6064820152608401610348565b6001600160a01b038116610d8c5760405162461bcd60e51b8152602060048201526024808201527f6368617267653a20726563686172676520746f20746865207a65726f206164646044820152637265737360e01b6064820152608401610348565b806001600160a01b0316826001600160a01b03161415610dee5760405162461bcd60e51b815260206004820181905260248201527f6368617267653a206e6f207265636861726765206973206e65636573736172796044820152606401610348565b610df882826111f9565b6106375760405162461bcd60e51b815260206004820152601e60248201527f6368617267653a206e6f207265636861726765207065726d697373696f6e00006044820152606401610348565b6001600160a01b03831615610eec5780610e5d846106fb565b1015610eb95760405162461bcd60e51b815260206004820152602560248201527f6368617267653a206163636f756e742062616c616e6365206973206e6f7420656044820152640dcdeeaced60db1b6064820152608401610348565b6001600160a01b038316600090815260cb602052604081208054839290610ee1908490611e44565b90915550610f049050565b8060c96000828254610efe9190611e5b565b90915550505b6001600160a01b038216600090815260cb602052604081208054839290610f2c908490611e5b565b9091555050505050565b7f360894a13ba1a3210667c828492db98dca3e2076cc3735a920a3ca505d382bbc546001600160a01b031690565b6033546001600160a01b0316331461057e5760405162461bcd60e51b815260040161034890611d38565b6000610f98610f36565b9050610fa38461167b565b600083511180610fb05750815b15610fc157610fbf8484611720565b505b7f4910fdfa16fed3260ed0e7147f7cc6da11a60208b5b9406d12a635614ffd9143805460ff166110d257805460ff191660011781556040516001600160a01b038316602482015261104090869060440160408051601f198184030181529190526020810180516001600160e01b0316631b2ce7f360e11b179052611720565b50805460ff19168155611051610f36565b6001600160a01b0316826001600160a01b0316146110c95760405162461bcd60e51b815260206004820152602f60248201527f45524331393637557067726164653a207570677261646520627265616b73206660448201526e75727468657220757067726164657360881b6064820152608401610348565b6110d285611802565b5050505050565b603380546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b600054610100900460ff1680611144575060005460ff16155b6111605760405162461bcd60e51b815260040161034890611d6d565b600054610100900460ff16158015611182576000805461ffff19166101011790555b61118a611842565b6107dc6118ac565b600054610100900460ff16806111ab575060005460ff16155b6111c75760405162461bcd60e51b815260040161034890611d6d565b600054610100900460ff161580156111e9576000805461ffff19166101011790555b6111f1611842565b6107dc611842565b60006112396040805160e0810182526060808252602082015290810160008152606060208201526040016000815260200160008152602001606081525090565b60cc5460405163fbcbc0f160e01b81526001600160a01b0386811660048301529091169063fbcbc0f190602401600060405180830381865afa158015611283573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526112ab9190810190611f0e565b5092935090918560408101606082016080830160a084018560018111156112d4576112d4611dbb565b60018111156112e5576112e5611dbb565b90528560018111156112f9576112f9611dbb565b600181111561130a5761130a611dbb565b905285905285600281111561132157611321611dbb565b600281111561133257611332611dbb565b9052949094525060019250611345915050565b8160800151600181111561135b5761135b611dbb565b14801561137d575060018160a00151600181111561137b5761137b611dbb565b145b6113c95760405162461bcd60e51b815260206004820152601960248201527f6368617267653a206163636f756e742069732066726f7a656e000000000000006044820152606401610348565b6114076040805160e0810182526060808252602082015290810160008152606060208201526040016000815260200160008152602001606081525090565b60cc5460405163fbcbc0f160e01b81526001600160a01b0386811660048301529091169063fbcbc0f190602401600060405180830381865afa158015611451573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526114799190810190611f0e565b5092935090918560408101606082016080830160a084018560018111156114a2576114a2611dbb565b60018111156114b3576114b3611dbb565b90528560018111156114c7576114c7611dbb565b60018111156114d8576114d8611dbb565b90528590528560028111156114ef576114ef611dbb565b600281111561150057611500611dbb565b9052949094525060019250611513915050565b8160800151600181111561152957611529611dbb565b14801561154b575060018160a00151600181111561154957611549611dbb565b145b61158e5760405162461bcd60e51b815260206004820152601460248201527331b430b933b29d103a379034b990333937bd32b760611b6044820152606401610348565b6002826040015160028111156115a6576115a6611dbb565b14156115f45760405162461bcd60e51b815260206004820152601e60248201527f6368617267653a206e6f207265636861726765207065726d697373696f6e00006044820152606401610348565b60008260400151600281111561160c5761160c611dbb565b14806116235750606081015182516116239161190c565b8061167257506060808201519083015161163c9161190c565b80156116505750805182516116509161190c565b8015611672575060028160400151600281111561166f5761166f611dbb565b14155b95945050505050565b803b6116df5760405162461bcd60e51b815260206004820152602d60248201527f455243313936373a206e657720696d706c656d656e746174696f6e206973206e60448201526c1bdd08184818dbdb9d1c9858dd609a1b6064820152608401610348565b7f360894a13ba1a3210667c828492db98dca3e2076cc3735a920a3ca505d382bbc80546001600160a01b0319166001600160a01b0392909216919091179055565b6060823b61177f5760405162461bcd60e51b815260206004820152602660248201527f416464726573733a2064656c65676174652063616c6c20746f206e6f6e2d636f6044820152651b9d1c9858dd60d21b6064820152608401610348565b600080846001600160a01b03168460405161179a9190611fed565b600060405180830381855af49150503d80600081146117d5576040519150601f19603f3d011682016040523d82523d6000602084013e6117da565b606091505b5091509150611672828260405180606001604052806027815260200161206e602791396119ad565b61180b8161167b565b6040516001600160a01b038216907fbc7cd75a20ee27fd9adebab32041f755214dbc6bffa90cc0225b39da2e5c2d3b90600090a250565b600054610100900460ff168061185b575060005460ff16155b6118775760405162461bcd60e51b815260040161034890611d6d565b600054610100900460ff161580156107dc576000805461ffff1916610101179055801561057e576000805461ff001916905550565b600054610100900460ff16806118c5575060005460ff16155b6118e15760405162461bcd60e51b815260040161034890611d6d565b600054610100900460ff16158015611903576000805461ffff19166101011790555b6107dc336110d9565b80518251600091849184911461192757600092505050610a57565b815160005b818110156119a05782818151811061194657611946612009565b602001015160f81c60f81b6001600160f81b03191684828151811061196d5761196d612009565b01602001516001600160f81b0319161461198e576000945050505050610a57565b806119988161201f565b91505061192c565b5060019695505050505050565b606083156119bc5750816119e6565b8251156119cc5782518084602001fd5b8160405162461bcd60e51b8152600401610348919061203a565b9392505050565b80356001600160a01b0381168114611a0457600080fd5b919050565b600060208284031215611a1b57600080fd5b6119e6826119ed565b80356001600160e01b031981168114611a0457600080fd5b60008060408385031215611a4f57600080fd5b611a58836119ed565b9150611a6660208401611a24565b90509250929050565b60008060408385031215611a8257600080fd5b611a8b836119ed565b946020939093013593505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715611ad857611ad8611a99565b604052919050565b600067ffffffffffffffff821115611afa57611afa611a99565b50601f01601f191660200190565b60008060408385031215611b1b57600080fd5b611b24836119ed565b9150602083013567ffffffffffffffff811115611b4057600080fd5b8301601f81018513611b5157600080fd5b8035611b64611b5f82611ae0565b611aaf565b818152866020838501011115611b7957600080fd5b816020840160208301376000602083830101528093505050509250929050565b600080600060608486031215611bae57600080fd5b611bb7846119ed565b9250611bc560208501611a24565b9150604084013563ffffffff81168114611bde57600080fd5b809150509250925092565b600060208284031215611bfb57600080fd5b5035919050565b600080600060608486031215611c1757600080fd5b611c20846119ed565b9250611c2e60208501611a24565b9150604084013590509250925092565b6020808252601390820152726368617267653a7a65726f206164647265737360681b604082015260600190565b6020808252818101527f6368617267653a206e6f207472616e73666572206973206e6563657373617279604082015260600190565b6020808252602c908201527f46756e6374696f6e206d7573742062652063616c6c6564207468726f7567682060408201526b19195b1959d85d1958d85b1b60a21b606082015260800190565b6020808252602c908201527f46756e6374696f6e206d7573742062652063616c6c6564207468726f7567682060408201526b6163746976652070726f787960a01b606082015260800190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b6020808252602e908201527f496e697469616c697a61626c653a20636f6e747261637420697320616c72656160408201526d191e481a5b9a5d1a585b1a5e995960921b606082015260800190565b634e487b7160e01b600052602160045260246000fd5b6001600160a01b03831681526040810160038310611dff57634e487b7160e01b600052602160045260246000fd5b8260208301529392505050565b600060208284031215611e1e57600080fd5b815180151581146119e657600080fd5b634e487b7160e01b600052601160045260246000fd5b600082821015611e5657611e56611e2e565b500390565b60008219821115611e6e57611e6e611e2e565b500190565b60005b83811015611e8e578181015183820152602001611e76565b83811115611e9d576000848401525b50505050565b600082601f830112611eb457600080fd5b8151611ec2611b5f82611ae0565b818152846020838601011115611ed757600080fd5b611ee8826020830160208701611e73565b949350505050565b805160038110611a0457600080fd5b805160028110611a0457600080fd5b600080600080600080600060e0888a031215611f2957600080fd5b875167ffffffffffffffff80821115611f4157600080fd5b611f4d8b838c01611ea3565b985060208a0151915080821115611f6357600080fd5b611f6f8b838c01611ea3565b9750611f7d60408b01611ef0565b965060608a0151915080821115611f9357600080fd5b611f9f8b838c01611ea3565b9550611fad60808b01611eff565b9450611fbb60a08b01611eff565b935060c08a0151915080821115611fd157600080fd5b50611fde8a828b01611ea3565b91505092959891949750929550565b60008251611fff818460208701611e73565b9190910192915050565b634e487b7160e01b600052603260045260246000fd5b600060001982141561203357612033611e2e565b5060010190565b6020815260008251806020840152612059816040850160208701611e73565b601f01601f1916919091016040019291505056fe416464726573733a206c6f772d6c6576656c2064656c65676174652063616c6c206661696c6564a2646970667358221220df6cfa1c141a84dd0e3e7ea25be18b487edeb9467c1cd1ac9e5cf96b8da4033764736f6c634300080b0033";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DELDDC = "delDDC";

    public static final String FUNC_DELFEE = "delFee";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_QUERYFEE = "queryFee";

    public static final String FUNC_RECHARGE = "recharge";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SELFRECHARGE = "selfRecharge";

    public static final String FUNC_SETAUTHORITYPROXYADDRESS = "setAuthorityProxyAddress";

    public static final String FUNC_SETFEE = "setFee";

    public static final String FUNC_SETTLEMENT = "settlement";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UPGRADETO = "upgradeTo";

    public static final String FUNC_UPGRADETOANDCALL = "upgradeToAndCall";

    public static final Event ADMINCHANGED_EVENT = new Event("AdminChanged",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
            }, new TypeReference<Address>() {
            }));
    ;

    public static final Event BEACONUPGRADED_EVENT = new Event("BeaconUpgraded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }));
    ;

    public static final Event DELDDC_EVENT = new Event("DelDDC",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }));
    ;

    public static final Event DELFEE_EVENT = new Event("DelFee",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Bytes4>() {
            }));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Address>(true) {
            }));
    ;

    public static final Event PAY_EVENT = new Event("Pay",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Address>(true) {
            }, new TypeReference<Bytes4>() {
            }, new TypeReference<Uint32>() {
            }, new TypeReference<Uint256>() {
            }));
    ;

    public static final Event RECHARGE_EVENT = new Event("Recharge",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Address>(true) {
            }, new TypeReference<Uint256>() {
            }));
    ;

    public static final Event SETFEE_EVENT = new Event("SetFee",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Bytes4>() {
            }, new TypeReference<Uint32>() {
            }));
    ;

    public static final Event SETTLEMENT_EVENT = new Event("Settlement",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Address>(true) {
            }, new TypeReference<Uint256>() {
            }));
    ;

    public static final Event UPGRADED_EVENT = new Event("Upgraded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }));
    ;

    @Deprecated
    protected Charge(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Charge(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Charge(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Charge(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AdminChangedEventResponse> getAdminChangedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ADMINCHANGED_EVENT, transactionReceipt);
        ArrayList<AdminChangedEventResponse> responses = new ArrayList<AdminChangedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AdminChangedEventResponse typedResponse = new AdminChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousAdmin = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newAdmin = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AdminChangedEventResponse> adminChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AdminChangedEventResponse>() {
            @Override
            public AdminChangedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ADMINCHANGED_EVENT, log);
                AdminChangedEventResponse typedResponse = new AdminChangedEventResponse();
                typedResponse.log = log;
                typedResponse.previousAdmin = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.newAdmin = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AdminChangedEventResponse> adminChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADMINCHANGED_EVENT));
        return adminChangedEventFlowable(filter);
    }

    public List<BeaconUpgradedEventResponse> getBeaconUpgradedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(BEACONUPGRADED_EVENT, transactionReceipt);
        ArrayList<BeaconUpgradedEventResponse> responses = new ArrayList<BeaconUpgradedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BeaconUpgradedEventResponse typedResponse = new BeaconUpgradedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.beacon = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BeaconUpgradedEventResponse> beaconUpgradedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BeaconUpgradedEventResponse>() {
            @Override
            public BeaconUpgradedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BEACONUPGRADED_EVENT, log);
                BeaconUpgradedEventResponse typedResponse = new BeaconUpgradedEventResponse();
                typedResponse.log = log;
                typedResponse.beacon = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BeaconUpgradedEventResponse> beaconUpgradedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BEACONUPGRADED_EVENT));
        return beaconUpgradedEventFlowable(filter);
    }

    public List<DelDDCEventResponse> getDelDDCEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(DELDDC_EVENT, transactionReceipt);
        ArrayList<DelDDCEventResponse> responses = new ArrayList<DelDDCEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DelDDCEventResponse typedResponse = new DelDDCEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DelDDCEventResponse> delDDCEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DelDDCEventResponse>() {
            @Override
            public DelDDCEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(DELDDC_EVENT, log);
                DelDDCEventResponse typedResponse = new DelDDCEventResponse();
                typedResponse.log = log;
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DelDDCEventResponse> delDDCEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELDDC_EVENT));
        return delDDCEventFlowable(filter);
    }

    public List<DelFeeEventResponse> getDelFeeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(DELFEE_EVENT, transactionReceipt);
        ArrayList<DelFeeEventResponse> responses = new ArrayList<DelFeeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DelFeeEventResponse typedResponse = new DelFeeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DelFeeEventResponse> delFeeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DelFeeEventResponse>() {
            @Override
            public DelFeeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(DELFEE_EVENT, log);
                DelFeeEventResponse typedResponse = new DelFeeEventResponse();
                typedResponse.log = log;
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DelFeeEventResponse> delFeeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELFEE_EVENT));
        return delFeeEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<PayEventResponse> getPayEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PAY_EVENT, transactionReceipt);
        ArrayList<PayEventResponse> responses = new ArrayList<PayEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PayEventResponse typedResponse = new PayEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.payer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.payee = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.ddcId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PayEventResponse> payEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PayEventResponse>() {
            @Override
            public PayEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PAY_EVENT, log);
                PayEventResponse typedResponse = new PayEventResponse();
                typedResponse.log = log;
                typedResponse.payer = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.payee = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.ddcId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PayEventResponse> payEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAY_EVENT));
        return payEventFlowable(filter);
    }

    public List<RechargeEventResponse> getRechargeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(RECHARGE_EVENT, transactionReceipt);
        ArrayList<RechargeEventResponse> responses = new ArrayList<RechargeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RechargeEventResponse typedResponse = new RechargeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RechargeEventResponse> rechargeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RechargeEventResponse>() {
            @Override
            public RechargeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(RECHARGE_EVENT, log);
                RechargeEventResponse typedResponse = new RechargeEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RechargeEventResponse> rechargeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECHARGE_EVENT));
        return rechargeEventFlowable(filter);
    }

    public List<SetFeeEventResponse> getSetFeeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SETFEE_EVENT, transactionReceipt);
        ArrayList<SetFeeEventResponse> responses = new ArrayList<SetFeeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetFeeEventResponse typedResponse = new SetFeeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SetFeeEventResponse> setFeeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SetFeeEventResponse>() {
            @Override
            public SetFeeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SETFEE_EVENT, log);
                SetFeeEventResponse typedResponse = new SetFeeEventResponse();
                typedResponse.log = log;
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SetFeeEventResponse> setFeeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETFEE_EVENT));
        return setFeeEventFlowable(filter);
    }

    public List<SettlementEventResponse> getSettlementEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SETTLEMENT_EVENT, transactionReceipt);
        ArrayList<SettlementEventResponse> responses = new ArrayList<SettlementEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SettlementEventResponse typedResponse = new SettlementEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.accAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SettlementEventResponse> settlementEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SettlementEventResponse>() {
            @Override
            public SettlementEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SETTLEMENT_EVENT, log);
                SettlementEventResponse typedResponse = new SettlementEventResponse();
                typedResponse.log = log;
                typedResponse.accAddr = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SettlementEventResponse> settlementEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETTLEMENT_EVENT));
        return settlementEventFlowable(filter);
    }

    public List<UpgradedEventResponse> getUpgradedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(UPGRADED_EVENT, transactionReceipt);
        ArrayList<UpgradedEventResponse> responses = new ArrayList<UpgradedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UpgradedEventResponse typedResponse = new UpgradedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.implementation = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UpgradedEventResponse> upgradedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UpgradedEventResponse>() {
            @Override
            public UpgradedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(UPGRADED_EVENT, log);
                UpgradedEventResponse typedResponse = new UpgradedEventResponse();
                typedResponse.log = log;
                typedResponse.implementation = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UpgradedEventResponse> upgradedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPGRADED_EVENT));
        return upgradedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String accAddr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(new Address(160, accAddr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> delDDC(String ddcAddr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELDDC,
                Arrays.<Type>asList(new Address(160, ddcAddr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> delFee(String ddcAddr, byte[] sig) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELFEE,
                Arrays.<Type>asList(new Address(160, ddcAddr),
                        new Bytes4(sig)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pay(String payer, byte[] sig, BigInteger ddcId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAY,
                Arrays.<Type>asList(new Address(160, payer),
                        new Bytes4(sig),
                        new Uint256(ddcId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> queryFee(String ddcAddr, byte[] sig) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_QUERYFEE,
                Arrays.<Type>asList(new Address(160, ddcAddr),
                        new Bytes4(sig)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> recharge(String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RECHARGE,
                Arrays.<Type>asList(new Address(160, to),
                        new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> selfRecharge(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELFRECHARGE,
                Arrays.<Type>asList(new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAuthorityProxyAddress(String authorityProxyAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAUTHORITYPROXYADDRESS,
                Arrays.<Type>asList(new Address(160, authorityProxyAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setFee(String ddcAddr, byte[] sig, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETFEE,
                Arrays.<Type>asList(new Address(160, ddcAddr),
                        new Bytes4(sig),
                        new Uint32(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> settlement(String ddcAddr, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETTLEMENT,
                Arrays.<Type>asList(new Address(160, ddcAddr),
                        new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> upgradeTo(String newImplementation) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPGRADETO,
                Arrays.<Type>asList(new Address(160, newImplementation)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> upgradeToAndCall(String newImplementation, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPGRADETOANDCALL,
                Arrays.<Type>asList(new Address(160, newImplementation),
                        new org.web3j.abi.datatypes.DynamicBytes(data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Charge load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Charge(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Charge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Charge(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Charge load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Charge(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Charge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Charge(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Charge> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Charge.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Charge> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Charge.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Charge> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Charge.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Charge> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Charge.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AdminChangedEventResponse extends BaseEventResponse {
        public String previousAdmin;

        public String newAdmin;
    }

    public static class BeaconUpgradedEventResponse extends BaseEventResponse {
        public String beacon;
    }

    public static class DelDDCEventResponse extends BaseEventResponse {
        public String ddcAddr;
    }

    public static class DelFeeEventResponse extends BaseEventResponse {
        public String ddcAddr;

        public byte[] sig;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PayEventResponse extends BaseEventResponse {
        public String payer;

        public String payee;

        public byte[] sig;

        public BigInteger amount;

        public BigInteger ddcId;
    }

    public static class RechargeEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger amount;
    }

    public static class SetFeeEventResponse extends BaseEventResponse {
        public String ddcAddr;

        public byte[] sig;

        public BigInteger amount;
    }

    public static class SettlementEventResponse extends BaseEventResponse {
        public String accAddr;

        public String ddcAddr;

        public BigInteger amount;
    }

    public static class UpgradedEventResponse extends BaseEventResponse {
        public String implementation;
    }
}
