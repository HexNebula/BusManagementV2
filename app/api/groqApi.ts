//import os from 'os';

import fetch from "node-fetch";
import { Groq } from 'groq-sdk';

const GROQ_API_KEY = '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';

const client = new Groq({
  apiKey: GROQ_API_KEY,
  dangerouslyAllowBrowser: true,
});

export const fetchChatCompletion = async (message: string): Promise<string> => {
  try {
    const completion = await client.chat.completions.create({
      messages: [
        {
          role: 'user',
          content: message,
        },
      ],
      model: 'mixtral-8x7b-32768',
      temperature: 0.5,
      max_tokens: 1024,
    });

    return completion.choices[0]?.message?.content || 'No response generated';
  } catch (error) {
    console.error('Error in fetchChatCompletion:', error);
    throw error;
  }
};