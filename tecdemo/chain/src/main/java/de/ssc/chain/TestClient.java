package de.ssc.chain;

import com.chain.api.Account;
import com.chain.api.Asset;
import com.chain.api.MockHsm;
import com.chain.api.Transaction;
import com.chain.http.Client;
import com.chain.signing.HsmSigner;

public class TestClient {
	public static void main(String[] args) throws Exception {
		Client client = new Client("http://ec2-52-59-213-218.eu-central-1.compute.amazonaws.com:1999/",
				"client:0997a5fad40fc96ec0e07b5aca49b85e67f18e0c6897dc62df95b731f3400d8b");
		MockHsm.Key key = MockHsm.Key.create(client);
		HsmSigner.addKey(key, MockHsm.getSignerClient(client));

		new Asset.Builder()
			.setAlias("gold")
			.addRootXpub(key.xpub)
			.setQuorum(1)
			.create(client);

		new Account.Builder()
			.setAlias("alice")
			.addRootXpub(key.xpub)
			.setQuorum(1)
			.create(client);

		Transaction.Template issuance = new Transaction.Builder()
				.addAction(new Transaction.Action.Issue().setAssetAlias("gold").setAmount(100))
				.addAction(new Transaction.Action.ControlWithAccount().setAccountAlias("alice").setAssetAlias("gold").setAmount(100))
				.build(client);

		Transaction.submit(client, HsmSigner.sign(issuance));
	}
}
