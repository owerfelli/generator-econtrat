'use strict';

const _ = require('lodash');
const chalk = require('chalk');

var Generator = require('yeoman-generator');
const CommonDBTypes = {
    STRING: 'String',
    INTEGER: 'Integer',
    LONG: 'Long',
    BIG_DECIMAL: 'BigDecimal',
    FLOAT: 'Float',
    DOUBLE: 'Double',
    UUID: 'UUID',
    ENUM: 'Enum',
    BOOLEAN: 'Boolean',
    LOCAL_DATE: 'LocalDate',
    ZONED_DATE_TIME: 'ZonedDateTime',
    BLOB: 'Blob',
    ANY_BLOB: 'AnyBlob',
    IMAGE_BLOB: 'ImageBlob',
    TEXT_BLOB: 'TextBlob',
    INSTANT: 'Instant',
    DURATION: 'Duration',
};
const {
    BIG_DECIMAL,
    BOOLEAN,
    DOUBLE,
    DURATION,
    ENUM,
    FLOAT,
    INTEGER,
    INSTANT,
    LOCAL_DATE,
    LONG,
    STRING,
    UUID,
    ZONED_DATE_TIME,
} = CommonDBTypes;

const Validations = {
    REQUIRED: 'required',
    UNIQUE: 'unique',
    MIN: 'min',
    MAX: 'max',
    MINLENGTH: 'minlength',
    MAXLENGTH: 'maxlength',
    PATTERN: 'pattern',
    MINBYTES: 'minbytes',
    MAXBYTES: 'maxbytes',
};
const {REQUIRED, UNIQUE, MAX, MAXBYTES, MAXLENGTH, MIN, MINBYTES, MINLENGTH, PATTERN} = Validations;
module.exports = class extends Generator {

    constructor(args, opts) {
        // Calling the super constructor is important so our generator is correctly set up
        super(args, opts);
        this.log(args);
    }

    writing() {

    }

    prompting() {
        return this.prompt([
            {
                type: 'input',
                name: 'id',
                option: {name: 'id', config: {alias: 'i', desc: 'Domain entity', type: String}},
                message: 'What ' + chalk.yellow('domain entity') + ' name?'
            },
            {
                type: 'input',
                name: 'package',
                option: {name: 'package', config: {alias: 'i', desc: 'package', type: String}},
                message: 'What your ' + chalk.yellow('package') + ' name?',
                default: ['com.elis'],
            }


        ]).then((answers) => {

            this.context = {
                classPackage: answers.package,
                className: _.upperFirst(_.camelCase(answers.id)),
                attributeName: _.lowerFirst(_.camelCase(answers.id)),
                snakeName: _.lowerFirst(_.snakeCase(answers.id)),
                domainName: answers.id
            }
            this.fields = [];
            this.relationships = [];
        });
        this.printFields();
    }

    printFields() {
        const context = this.context;
        this.log(chalk.green(`\nGenerating field\n`));
        const prompts = [

            {
                type: 'input',
                name: 'entity',

                message: 'Fill your object?',
            }
        ];
        return this.prompt(prompts).then(props => {
            let entity = JSON.parse(props.entity);
            for (const fieldName in entity) {
                console.log(fieldName, entity[fieldName])
                const field = {
                    fieldName: fieldName,
                    fieldType: entity[fieldName],
                    dbName: _.snakeCase(fieldName) ,
                    capitalFieldName: _.upperFirst(fieldName) ,
                    fieldTypeBlobContent: props.fieldTypeBlobContent,
                    fieldValues: props.fieldValues,
                    fieldValidateRules: props.fieldValidateRules,
                    fieldValidateRulesMinlength: props.fieldValidateRulesMinlength,
                    fieldValidateRulesMaxlength: props.fieldValidateRulesMaxlength,
                    fieldValidateRulesPattern: props.fieldValidateRulesPattern,
                    fieldValidateRulesMin: props.fieldValidateRulesMin,
                    fieldValidateRulesMax: props.fieldValidateRulesMax,
                    fieldValidateRulesMinbytes: props.fieldValidateRulesMinbytes,
                    fieldValidateRulesMaxbytes: props.fieldValidateRulesMaxbytes,
                };
                this.fields = this.fields.concat(field);
            }
            context.fields = this.fields;
            context.relationships = [];
            this.fs.copyTpl(this.templatePath('**/*'), this.destinationPath(), context);
        })
    }
}
