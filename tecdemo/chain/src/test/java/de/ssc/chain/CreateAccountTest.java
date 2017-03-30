package de.ssc.chain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.chain.api.Account;
import com.chain.api.Asset;
import com.chain.api.ControlProgram;
import com.chain.api.MockHsm;
import com.chain.api.Transaction;
import com.chain.http.BatchResponse;
import com.chain.http.Client;

public class CreateAccountTest {

	  static Client client;
	  static MockHsm.Key key;

	  @Test
	  public void run() throws Exception {
//		testKeyCreate();
//	    testAccountCreate();
//	    testAssetCreate();
//	    testAssetCreateBatch();
	    	    
	    testControlProgramCreate();
	    testControlProgramCreateBatch();
	    testTransactionFeedCreate();
	  }
	  
		
	  public void testKeyCreate() throws Exception {
		  client = TestUtils.generateClient();
		  String alias = "CreateTest.testKeyCreate.Key_A";
		  key = MockHsm.Key.create(client, alias);
		  
		  assertNotNull(key.xpub);
		  assertEquals(alias, key.alias);
	  }
	  
	  
	  public void testAccountCreate() throws Exception {
		  client = TestUtils.generateClient();
		  key = MockHsm.Key.create(client);

		  String alice = "CreateTest.ALICE";
		  String test = "CreateTest.ALICE_TEST";
		  Map<String, Object> tags = new HashMap<>();
		    
		  tags.put("name", alice);
		  Account account = new Account.Builder()
		            			.setAlias(alice)
		            			.addRootXpub(key.xpub)
		            			.setQuorum(1)
		            			.setTags(tags)
		            			.addTag("test", test)
		            			.create(client);
		    
		    assertNotNull(account.id);
		    assertNotNull(account.keys);
		    assertEquals(1, account.keys.length);
		    assertNotNull(account.keys[0].accountXpub);
		    assertNotNull(account.keys[0].rootXpub);
		    assertNotNull(account.keys[0].accountDerivationPath);
		    assertEquals(alice, account.alias);
		    assertEquals(1, account.quorum);
		    assertEquals(alice, account.tags.get("name"));
		    assertEquals(test, account.tags.get("test"));
	  }

	  
	  public void testAccountCreateBatch() throws Exception {
		    client = TestUtils.generateClient();
		    key = MockHsm.Key.create(client);
		    String alice = "CreateTest.testAccountCreateBatch.alice";
		    Account.Builder builder =
		        new Account.Builder().setAlias(alice).addRootXpub(key.xpub).setQuorum(1);

		    Account.Builder failure =
		        new Account.Builder().setAlias(alice).addRootXpub(key.xpub).setQuorum(1);

		    BatchResponse<Account> resp = Account.createBatch(client, Arrays.asList(builder, failure));
		    assertEquals(1, resp.successes().size());
		    assertEquals(1, resp.errors().size());
	  }
	  
	  
	  public void testAssetCreate() throws Exception {
		    client = TestUtils.generateClient();
		    key = MockHsm.Key.create(client);
		    String asset = "CreateTest.testAssetCreate.asset";
		    String test = "CreateTest.testAssetCreate.test";
		    Map<String, Object> tags = new HashMap<>();
		    tags.put("name", asset);
		    Map<String, Object> def = new HashMap<>();
		    def.put("name", asset);
		    
		    Asset testAsset =
		        new Asset.Builder()
		            .setAlias(asset)
		            .addRootXpub(key.xpub)
		            .setQuorum(1)
		            .setTags(tags)
		            .addTag("test", test)
		            .setDefinition(def)
		            .addDefinitionField("test", test)
		            .create(client);
		    
		    assertNotNull(testAsset.id, testAsset.issuanceProgram);
		    assertNotNull(testAsset.issuanceProgram);
		    assertNotNull(testAsset.keys);
		    assertEquals(1, testAsset.keys.length);
		    assertNotNull(testAsset.keys[0].assetPubkey);
		    assertNotNull(testAsset.keys[0].rootXpub);
		    assertNotNull(testAsset.keys[0].assetDerivationPath);
		    assertEquals(asset, testAsset.alias);
		    assertEquals(1, testAsset.quorum);
		    assertEquals(asset, testAsset.tags.get("name"));
		    assertEquals(test, testAsset.tags.get("test"));
		    assertEquals(asset, testAsset.definition.get("name"));
		    assertEquals(test, testAsset.definition.get("test"));
		    assertEquals("yes", testAsset.isLocal);
		  }

	  
		  public void testAssetCreateBatch() throws Exception {
		    client = TestUtils.generateClient();
		    key = MockHsm.Key.create(client);
		    String asset = "CreateTest.testAssetCreateBatch.asset";
		    Asset.Builder builder = new Asset.Builder().setAlias(asset).addRootXpub(key.xpub).setQuorum(1);
		    Asset.Builder failure = new Asset.Builder().setAlias(asset).addRootXpub(key.xpub).setQuorum(1);
		    BatchResponse<Asset> resp = Asset.createBatch(client, Arrays.asList(builder, failure));
		    assertEquals(1, resp.successes().size());
		    assertEquals(1, resp.errors().size());
		  }
	  
	  
		  public void testControlProgramCreate() throws Exception {
			    client = TestUtils.generateClient();
			    key = MockHsm.Key.create(client);
			    String alice = "CreateTest.testControlProgramCreate.ALICE";
			    Account account =
			        new Account.Builder()
			            .setAlias(alice)
			            .addRootXpub(key.xpub)
			            .setQuorum(1)
			            .addTag("name", alice)
			            .create(client);

			    ControlProgram ctrlp =
			        new ControlProgram.Builder().controlWithAccountById(account.id).create(client);
			    assertNotNull(ctrlp.controlProgram);

			    ctrlp = new ControlProgram.Builder().controlWithAccountByAlias(account.alias).create(client);
			    assertNotNull(ctrlp.controlProgram);
		  }

		  public void testControlProgramCreateBatch() throws Exception {
			    client = TestUtils.generateClient();
			    key = MockHsm.Key.create(client);
			    String alice = "CreateTest.testControlProgramCreateBatch.ALICE";
			    Account account =
			        new Account.Builder()
			            .setAlias(alice)
			            .addRootXpub(key.xpub)
			            .setQuorum(1)
			            .addTag("name", alice)
			            .create(client);

			    ControlProgram.Builder builder =
			        new ControlProgram.Builder().controlWithAccountById(account.id);

			    ControlProgram.Builder failure = new ControlProgram.Builder().controlWithAccountById("bad-id");

			    BatchResponse<ControlProgram> resp =
			        ControlProgram.createBatch(client, Arrays.asList(builder, failure));
			    assertEquals(1, resp.successes().size());
			    assertEquals(1, resp.errors().size());
		  }
		  
		  public void testTransactionFeedCreate() throws Exception {
			    client = TestUtils.generateClient();
			    String alias = "CreateTest.testFeedCreate.feed";
			    String filter = "outputs(account_alias='alice')";
			    Transaction.Feed feed = Transaction.Feed.create(client, alias, filter);
			    assertNotNull(feed.id);
			    assertNotNull(feed.after);
			    assertEquals(alias, feed.alias);
			    assertEquals(filter, feed.filter);
		  }
		  
		 
}



