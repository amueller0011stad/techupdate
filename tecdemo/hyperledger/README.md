# Demo f&uuml;r Blockchains mit der IBM Hyperledger fabric 

- Server: Azure D1 Server mit Ubuntu 17.04, IP: 13.73.166.177
- Hyperledger fabric mit docker:

    CONTAINER ID        IMAGE    COMMAND                  CREATED             STATUS              PORTS           NAMES                                                                        
    1af8b5a90b04        dev-vp0-...   "/opt/gopath/bin/e..."   10 minutes ago      Up 10 minutes                       dev-vp0-...                                                             
    b05314308693        ibmblockchain/fabric-peer:x86_64-0.6.1-preview     "sh -c 'sleep 10; ..."   17 minutes ago      Up 15 minutes       0.0.0.0:7050-7051->7050-7051/tcp, 0.0.0.0:7053->7053/tcp   dockercompose_vp_1                                                           
    0fa5f5e9d1bc        ibmblockchain/fabric-membersrvc:x86_64-0.6.1-preview  "membersrvc"             17 minutes ago      Up 15 minutes       0.0.0.0:7054->7054/tcp  dockercompose_membersrvc_1                                                   

Ein Beispiel, es wird Chaincode deployed, eine Transaktion ausgef&uuml;t und der ge&auml;nderte Wert abgefragt. Die Chaincode ID aus den letzten beiden Kommands kommt vom deloy zur&uuml;ck.

    peer chaincode deploy -u test_user0 -p github.com/hyperledger/fabric/examples/chaincode/go/chaincode_example02 -c '{"Args": ["init","a", "100", "b", "200"]}'
    peer chaincode invoke -u test_user0 -n ee5b24a1f17c356dd5f6e37307922e39ddba12e5d2e203ed93401d7d05eb0dd194fb9070549c5dc31eb63f4e654dbd5a1d86cbb30c48e3ab1812590cd0f78539 -c '{"Args": ["invoke", "a", "b", "10"]}'
    peer chaincode query -u test_user0 -n ee5b24a1f17c356dd5f6e37307922e39ddba12e5d2e203ed93401d7d05eb0dd194fb9070549c5dc31eb63f4e654dbd5a1d86cbb30c48e3ab1812590cd0f78539 -c '{"Args": ["query", "a"]}'

Siehe [https://github.com/IBM-Blockchain/fabric-images](https://github.com/IBM-Blockchain/fabric-images)
