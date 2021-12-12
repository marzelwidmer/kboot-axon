-- drop table if exists association_value_entry cascade;
-- drop table if exists domain_event_entry cascade;
-- drop table if exists saga_entry cascade;
-- drop table if exists snapshot_event_entry cascade;
-- drop table if exists token_entry cascade;
-- drop sequence if exists hibernate_sequence;

create sequence IF NOT EXISTS hibernate_sequence;


create table IF NOT EXISTS association_value_entry
(
    id                int8         not null,
    association_key   varchar(255) not null,
    association_value varchar(255),
    saga_id           varchar(255) not null,
    saga_type         varchar(255),
    primary key (id)
);

create table IF NOT EXISTS domain_event_entry
(
    global_index         int8         not null,
    event_identifier     varchar(255) not null,
    meta_data            oid,
    payload              oid          not null,
    payload_revision     varchar(255),
    payload_type         varchar(255) not null,
    time_stamp           varchar(255) not null,
    aggregate_identifier varchar(255) not null,
    sequence_number      int8         not null,
    type                 varchar(255),
    primary key (global_index)
);

create table IF NOT EXISTS saga_entry
(
    saga_id         varchar(255) not null,
    revision        varchar(255),
    saga_type       varchar(255),
    serialized_saga oid,
    primary key (saga_id)
);

create table IF NOT EXISTS snapshot_event_entry
(
    aggregate_identifier varchar(255) not null,
    sequence_number      int8         not null,
    type                 varchar(255) not null,
    event_identifier     varchar(255) not null,
    meta_data            oid,
    payload              oid          not null,
    payload_revision     varchar(255),
    payload_type         varchar(255) not null,
    time_stamp           varchar(255) not null,
    primary key (aggregate_identifier, sequence_number, type)
);

create table IF NOT EXISTS token_entry
(
    processor_name varchar(255) not null,
    segment        int4         not null,
    owner          varchar(255),
    timestamp      varchar(255) not null,
    token          oid,
    token_type     varchar(255),
    primary key (processor_name, segment)
);

-- alter sequence hibernate_sequence owner to postgres;
-- alter table domain_event_entry add constraint UK8s1f994p4la2ipb13me2xqm1w unique (aggregate_identifier, sequence_number);
-- alter table domain_event_entry add constraint UK_fwe6lsa8bfo6hyas6ud3m8c7x unique (event_identifier);
-- alter table snapshot_event_entry add constraint UK_e1uucjseo68gopmnd0vgdl44h unique (event_identifier);

