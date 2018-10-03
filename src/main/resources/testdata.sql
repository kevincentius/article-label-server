
INSERT INTO article (title, content) VALUES ('Test article 1', 'This is the first article ever.');
INSERT INTO article (title, content) VALUES ('Test article 2', 'This is the second article.');

INSERT INTO question (pos, name, description) VALUES (1, 'Readability', 'How simple the text is to understand');
INSERT INTO question (pos, name, description) VALUES (2, 'Bias', 'Political bias. Left means something and right means another.');
INSERT INTO question (pos, name, description) VALUES (3, 'Sentiment', 'Sentiment description here');

INSERT INTO choice (question, pos, name) SELECT q.id, 1, 'Too simple' FROM question q WHERE q.name = 'Readability';
INSERT INTO choice (question, pos, name) SELECT q.id, 2, 'Average' FROM question q WHERE q.name = 'Readability';
INSERT INTO choice (question, pos, name) SELECT q.id, 3, 'Complex' FROM question q WHERE q.name = 'Readability';

INSERT INTO choice (question, pos, name) SELECT q.id, 1, 'Left' FROM question q WHERE q.name = 'Bias';
INSERT INTO choice (question, pos, name) SELECT q.id, 2, 'Neutral' FROM question q WHERE q.name = 'Bias';
INSERT INTO choice (question, pos, name) SELECT q.id, 3, 'Right' FROM question q WHERE q.name = 'Bias';

INSERT INTO choice (question, pos, name) SELECT q.id, 1, 'Negative' FROM question q WHERE q.name = 'Sentiment';
INSERT INTO choice (question, pos, name) SELECT q.id, 2, 'Neutral' FROM question q WHERE q.name = 'Sentiment';
INSERT INTO choice (question, pos, name) SELECT q.id, 3, 'Positive' FROM question q WHERE q.name = 'Sentiment';
INSERT INTO choice (question, pos, name) SELECT q.id, 4, 'Mixed' FROM question q WHERE q.name = 'Sentiment';
