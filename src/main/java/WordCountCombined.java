/*
 * Copyright 2018 InfAI (CC SES)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.infai.seits.sepl.operators.Message;
import org.infai.seits.sepl.operators.OperatorInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WordCountCombined implements OperatorInterface {

    Map<String, Integer> map = new HashMap<>();

    @Override
    public void run(Message message) {
        //Splitting
        String sentence = message.getInput("sentence").getString();
        String[] words = sentence.split("(\\W+)");

        //Counting
        for(String word: words){
            if (map.containsKey(word)){
                int value = map.get(word);
                value++;
                map.replace(word, value);
            }
            else{
                map.put(word, 1);
            }
        }
        JSONObject wordCounts = new JSONObject();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            wordCounts.put(entry.getKey(), entry.getValue());

        }

        message.output("wordcounts", wordCounts.toMap());
    }

    @Override
    public void config(Message message) {
        message.addInput("sentence");
    }
}
